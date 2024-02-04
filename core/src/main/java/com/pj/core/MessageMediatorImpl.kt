package com.pj.core

import java.nio.file.NotDirectoryException

internal class MessageMediatorImpl : MessageMediator {

    private val ReceiveAny = "$.ReceiveAny"

    private val nodeFilter : MutableMap<String, MutableList<MessageNode>> by lazy { mutableMapOf() }
    private val nodeMap : MutableMap<Int, MessageNode> by lazy { mutableMapOf() }
    override fun register(node: MessageNode) {
        nodeMap[node.id] = node
    }

    override fun registerType(node: MessageNode, type: String) {
        val list = nodeFilter.getOrPut(type) { mutableListOf() }
        if(!list.contains(node))
            list.add(node)
    }


    override fun notify(message: Message, notifier: Notifier){
        val box = MessagePostman(message, this.linkReceivable(notifier))
        val nodes = nodeFilter.getOrDefault(message.type, null)
        nodes?.forEach { receiver ->
            if(receiver.id != notifier.id)
                receiver.onReceive(box)
        }

        nodeFilter[ReceiveAny]?.forEach{receiver ->
            if(notifier.id !=  receiver.id)
                receiver.onReceive(box)
        }
    }

    override fun notify(message: Message, notifier: Notifier, receiver: Receivable) {
        val box = MessagePostman(message, linkReceivable(notifier))
        receiver.onReceive(box)
    }

    override fun giveBack(message: Message, giveBacked: Receivable) {
        val box = MessagePostman(message)
        giveBacked.onReceive(box)
    }

    private fun linkReceivable(notifier: Notifier) : Receivable{
        return nodeMap[notifier.id] as Receivable
    }

}