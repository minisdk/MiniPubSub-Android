package com.minisdk.pubsub

import com.minisdk.pubsub.data.SdkType

class Messenger(private val target: SdkType = SdkType.Native) : Publisher() {

    fun subscribe(key: String, delegate: ReceiveDelegate) {
        MessageManager.mediator.register(Receiver(id, key, target, delegate))
    }

    fun unsubscribe(key: String) {
        MessageManager.mediator.unregister(id, key)
    }

    fun handle(key: String, delegate: HandleDelegate){
        MessageManager.mediator.handle(key, Handler(id, key, target, delegate))
    }

}