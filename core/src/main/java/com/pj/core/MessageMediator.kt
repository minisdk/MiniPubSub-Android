package com.pj.core

interface MessageMediator {
    fun register(node: MessageNode)
    fun notify(message: Message, tag: Tag, notifier: Notifier)
    fun giveBack(message: Message, giveBacked: Receivable)
}