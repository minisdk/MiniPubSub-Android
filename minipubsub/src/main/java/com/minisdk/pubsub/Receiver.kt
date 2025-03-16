package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

typealias ReceiveDelegate = (Message) -> Unit
data class Receiver(
    val nodeId: Int,
    val key: String,
    val delegate: ReceiveDelegate)