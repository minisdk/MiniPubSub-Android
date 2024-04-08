package com.pj.pubsub

import com.pj.pubsub.proto.NativePubSub.Envelope

internal class MessageMediatorImpl : MessageMediator {

    private val idFilter : MutableMap<Int, ReceivablePublisher> by lazy { mutableMapOf() }
    override fun register(node: ReceivablePublisher) {
        idFilter[node.id] = node
    }

    override fun publish(envelope: Envelope, tag: Tag) {
        if(envelope.hasReceiverID()){
            val receiver = idFilter[envelope.receiverID]
            if(receiver != null){
                val channel = ChannelConnection(envelope, receiver.id, tag)
                receiver.onReceive(channel)
            }
            else
            {
                this.broadcast(envelope, tag)
            }
        }
        else{
            this.broadcast(envelope, tag)
        }
    }

    private fun broadcast(envelope: Envelope, tag: Tag){
        idFilter.values.filter { receiver ->
            receiver.matchTag(tag) && receiver.id != envelope.senderID
        }.forEach { receiver ->
            val channel = ChannelConnection(envelope, receiver.id, tag)
            receiver.onReceive(channel)
        }
    }

}