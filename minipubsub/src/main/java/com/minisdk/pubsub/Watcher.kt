package com.minisdk.pubsub

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