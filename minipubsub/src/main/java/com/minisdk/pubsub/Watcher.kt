package com.minisdk.pubsub

import com.minisdk.pubsub.data.SdkType

class Watcher(private val target: SdkType = SdkType.Native) : Node(){
    private val watcherKey = "Key_Watcher_Reserved"

    private val publisher = Publisher()

    fun watch(delegate: ReceiveDelegate) {
        MessageManager.mediator.register(Receiver(id, watcherKey, target, delegate))
    }

    fun unwatch() {
        MessageManager.mediator.unregister(id, watcherKey)
    }
}