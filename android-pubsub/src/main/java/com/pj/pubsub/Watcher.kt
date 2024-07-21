package com.pj.pubsub

import com.pj.pubsub.data.Message

class Watcher : Watchable{

    private val publisher = Publisher()

    override val id: Int = publisher.id

    override fun watch(delegate: (Message) -> Unit) {
        MessageManager.mediator.watch(Receiver(id, "", delegate))
    }

    override fun unwatch() {
        MessageManager.mediator.unwatch(id)
    }

    fun publish(message: Message) {
        MessageManager.mediator.publish(message, id)
    }

}