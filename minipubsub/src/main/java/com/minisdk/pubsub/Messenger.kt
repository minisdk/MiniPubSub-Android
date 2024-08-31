package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

class Messenger() : Node{

    private val publisher: Publisher = Publisher()

    override val id = publisher.id
    fun subscribe(key: String, delegate: ReceiverDelegate) {
        MessageManager.mediator.register(Receiver(id, key, delegate))
    }

    fun unsubscribe(key: String) {
        MessageManager.mediator.unregister(id, key)
    }

    fun publish(message: Message){
        publisher.publish(message)
    }
}