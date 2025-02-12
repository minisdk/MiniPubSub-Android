package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Request

interface MessageMediator {
    fun register(receiver: Receiver)
    fun unregister(id: Int, key: String)
    fun registerInstantReceiver(receiver: Receiver)
    fun broadcast(request: Request)
}