package com.pj.core

import com.pj.core.extensions.Message

interface MessageMediator {
    fun register(node: MessageNode)
    fun notify(message: Message, tag: Tag, notifier: Notifier)
    fun giveBack(message: Message, giveBacked: Receivable)
}