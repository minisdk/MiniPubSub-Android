package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Request

class Watcher : Node(){
    private val watcherKey = "Key_Watcher_Reserved"

    private val publisher = Publisher()

    fun watch(delegate: ReceiveDelegate) {
        MessageManager.mediator.register(Receiver(id, watcherKey, delegate))
    }

    fun unwatch() {
        MessageManager.mediator.unregister(id, watcherKey)
    }
}