package com.pj.core

interface MessageMediator {
    fun register(node: MessageNode)
    fun registerType(node: MessageNode, type: String)
    fun notify(message: Message, notifier: Notifier)
    fun notify(message: Message, notifier: Notifier, receiver: Receivable)
    fun giveBack(message: Message, giveBacked: Receivable)
}