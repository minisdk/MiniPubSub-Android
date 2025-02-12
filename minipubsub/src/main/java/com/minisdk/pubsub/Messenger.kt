package com.minisdk.pubsub

class Messenger() : Publisher() {

    private val publisher: Publisher = Publisher()

    fun subscribe(key: String, delegate: ReceiveDelegate) {
        MessageManager.mediator.register(Receiver(id, key, delegate))
    }

    fun unsubscribe(key: String) {
        MessageManager.mediator.unregister(id, key)
    }

}