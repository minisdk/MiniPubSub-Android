package com.pj.pubsub.extensions

import com.pj.pubsub.proto.NativePubSub
import com.pj.pubsub.proto.NativePubSub.Envelope

fun Envelope(message: Message, senderID: Int): Envelope{
    val builder = Envelope.newBuilder()
    builder.message = message
    builder.senderID = senderID
    return builder.build()
}

fun Envelope(message: Message, sendierID: Int, receiverID: Int): Envelope{
    val builder = Envelope.newBuilder()
    builder.message = message
    builder.senderID = sendierID
    builder.receiverID = receiverID
    return builder.build()
}
