package com.minisdk.pubsub

import com.minisdk.pubsub.data.Request

typealias ReceiveDelegate = (Request) -> Unit
data class Receiver(
    val nodeId: Int,
    val key: String,
    val delegate: ReceiveDelegate)