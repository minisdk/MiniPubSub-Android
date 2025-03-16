package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

interface MessageMediator {
    fun register(receiver: Receiver)
    fun unregister(id: Int, key: String)
    fun registerInstantReceiver(receiver: Receiver)
    fun broadcast(message: Message)
}