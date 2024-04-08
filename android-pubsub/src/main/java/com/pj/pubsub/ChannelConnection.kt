package com.pj.pubsub

import com.pj.pubsub.extensions.Envelope
import com.pj.pubsub.extensions.Message
import com.pj.pubsub.proto.NativePubSub.Envelope

internal class ChannelConnection(envelope: Envelope, receiverID: Int, tag: Tag) : Channel{

    val envelope: Envelope
    private val tag: Tag
    private val senderID: Int
    private val receiverID: Int
    override val message: Message

    init {
        this.envelope = envelope
        this.tag = tag
        this.message = envelope.message
        this.senderID = envelope.senderID
        this.receiverID = receiverID
    }

    override fun reply(message: Message){
        val envelope = Envelope(message, this.receiverID, this.senderID)
        MessageManager.mediator.publish(envelope, Tag.none)

    }

    fun serializeTag(){
        tag.names.forEach { name ->
            envelope.tagNamesList.add(name)
        }
    }
}