package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

internal class MessageMediatorImpl : MessageMediator {
    private val TAG = MessageMediator::class.java.name
    private val watcherKey = "Key_Watcher_Reserved"

    private val receiversMap: MutableMap<String, MutableList<Receiver>> = mutableMapOf()
    private val instantReceiverMap : MutableMap<String, Receiver> = mutableMapOf()

    override fun register(receiver: Receiver) {
        val receivers = receiversMap.getOrPut(receiver.key){
            mutableListOf()
        }
        receivers.add(receiver)
    }

    override fun unregister(id: Int, key: String) {
        val receivers = receiversMap[key]
        receivers?.removeAll{receiver ->
            receiver.nodeId == id
        }
    }

    override fun registerInstantReceiver(receiver: Receiver) {
        instantReceiverMap[receiver.key] = receiver
    }

    override fun broadcast(message: Message) {
        instantReceiverMap.remove(message.key)?.delegate?.invoke(message)

        receiversMap[message.key]?.forEach { receiver ->
            if(receiver.nodeId != message.info.nodeInfo.publisherId)
            {
                receiver.delegate.invoke(message)
            }
        }

        receiversMap[watcherKey]?.forEach {watcher ->
            if(watcher.nodeId != message.info.nodeInfo.publisherId)
            {
                watcher.delegate.invoke(message)
            }
        }
    }

}