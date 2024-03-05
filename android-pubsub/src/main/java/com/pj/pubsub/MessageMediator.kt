package com.pj.pubsub

import com.pj.pubsub.extensions.Message

interface MessageMediator {
    fun register(node: ReceivablePublisher)
    fun publish(message: Message, tag: Tag, publisher: Publisher)
    fun giveBack(message: Message, giveBacked: Receivable)
}