package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

typealias ReceiverDelegate = (Message) -> Unit
data class Receiver(
    val nodeId: Int,
    val key: String,
    val delegate: ReceiverDelegate)