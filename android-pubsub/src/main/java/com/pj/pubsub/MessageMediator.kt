package com.pj.pubsub

import com.pj.pubsub.extensions.Message
import com.pj.pubsub.proto.NativePubSub.Envelope

interface MessageMediator {
    fun register(node: ReceivablePublisher)
    fun publish(envelope: Envelope, tag: Tag)
}