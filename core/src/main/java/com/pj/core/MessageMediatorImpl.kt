package com.pj.core

internal class MessageMediatorImpl : MessageMediator {

    private val idFilter : MutableMap<Int, MessageNode> by lazy { mutableMapOf() }
    override fun register(node: MessageNode) {
        idFilter[node.id] = node
    }

    override fun notify(message: Message, tag: Tag, notifier: Notifier) {
        val holder = MessagePostman(message, this.linkReceivable(notifier))
        idFilter.values.filter { node ->
            node.tag.contains(tag) && node.hasKey(message.key)
        }.forEach { node ->
            node.onReceive(holder)
        }
    }

    override fun giveBack(message: Message, giveBacked: Receivable) {
        val box = MessagePostman(message)
        giveBacked.onReceive(box)
    }

    private fun linkReceivable(notifier: Notifier) : Receivable{
        return idFilter[notifier.id] as Receivable
    }

}