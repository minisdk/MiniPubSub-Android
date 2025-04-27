package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Payload

interface MessageMediator {
    fun register(receiver: Receiver)
    fun unregister(id: Int, key: String)
    fun registerInstantReceiver(receiver: Receiver)
    fun broadcast(message: Message)
    fun handle(handler: Handler)
    fun handleTarget(handler: Handler)
    fun sendSync(message: Message): Payload
}