package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.SdkType

interface MessageMediator {
    fun register(receiver: Receiver)
    fun unregister(id: Int, key: String)
    fun registerInstantReceiver(receiver: Receiver)
    fun broadcast(message: Message)
    fun handle(key:String, handler: Handler)
    fun handle(target: SdkType, handler: Handler)
    fun sendSync(message: Message): Payload
}