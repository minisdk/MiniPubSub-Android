package com.pj.core

interface MessageNotifier {
    fun notify(message : Message)
    fun notify(message: Message, target: MessageReceiver)
}