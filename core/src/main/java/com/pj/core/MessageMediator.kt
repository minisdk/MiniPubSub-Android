package com.pj.core

interface MessageMediator {
    fun add(receiver: MessageNode)
    fun add(receiver: MessageNode, eventType: String)
    fun notify(message: Message, notifier: MessageNode)
    fun notify(message: Message, notifier: MessageNode, receiver: MessageNode)
    fun giveBack(message: Message, giveBacked: MessageNode)
}