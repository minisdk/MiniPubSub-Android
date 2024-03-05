package com.pj.pubsub

import com.pj.pubsub.extensions.Message

internal class MessageMediatorImpl : MessageMediator {

    private val idFilter : MutableMap<Int, ReceivablePublisher> by lazy { mutableMapOf() }
    override fun register(node: ReceivablePublisher) {
        idFilter[node.id] = node
    }

    override fun publish(message: Message, tag: Tag, publisher: Publisher) {
        val holder = MessagePostman(message, this.linkReceivable(publisher))
        idFilter.values.filter { node ->
            node.tag.contains(tag) && node.id != publisher.id
        }.forEach { node ->
            node.onReceive(holder)
        }
    }

    override fun giveBack(message: Message, giveBacked: Receivable) {
        val box = MessagePostman(message)
        giveBacked.onReceive(box)
    }

    private fun linkReceivable(publisher: Publisher) : Receivable{
        return idFilter[publisher.id] as Receivable
    }

}