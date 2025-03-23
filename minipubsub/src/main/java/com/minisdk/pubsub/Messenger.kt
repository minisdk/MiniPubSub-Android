package com.minisdk.pubsub

import com.minisdk.pubsub.data.SdkType

class Messenger(private val target: SdkType = SdkType.Native) : Publisher() {

    fun subscribe(key: String, delegate: ReceiveDelegate) {
        MessageManager.mediator.register(Receiver(id, key, target, delegate))
    }

    fun unsubscribe(key: String) {
        MessageManager.mediator.unregister(id, key)
    }

}