package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

class Watcher : Node{

    private val publisher = Publisher()

    override val id: Int = publisher.id

    fun watch(delegate: (Message) -> Unit) {
        MessageManager.mediator.watch(Receiver(id, "", delegate))
    }

    fun unwatch() {
        MessageManager.mediator.unwatch(id)
    }

    fun publish(message: Message) {
        MessageManager.mediator.publish(message, id)
    }

}