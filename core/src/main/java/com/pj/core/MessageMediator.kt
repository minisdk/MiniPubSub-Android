package com.pj.core

interface MessageMediator {
    fun add(receiver: MessageReceiver)
    fun add(receiver: MessageReceiver, eventType: String)
    fun notify(message: Message, notifier: MessageNotifier)
    fun notify(message: Message, notifier: MessageNotifier, receiver: MessageReceiver)
    fun giveBack(message: Message, giveBacked: MessageNotifier)
}