package com.pj.pubsub

import com.pj.pubsub.data.Message

class Messenger() : Subscribable{

    private val publisher: Publisher = Publisher()

    override val id = publisher.id
    override fun subscribe(key: String, delegate: ReceiverDelegate) {
        MessageManager.mediator.register(Receiver(id, key, delegate))
    }

    override fun unsubscribe(key: String) {
        MessageManager.mediator.unregister(id, key)
    }

    fun publish(message: Message){
        publisher.publish(message)
    }
}