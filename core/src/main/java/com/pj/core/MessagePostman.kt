package com.pj.core


class MessagePostman(message: Message, receiver: MessageNode?) : MessageBox{
    private val notifier : MessageNode?
    override val message: Message

    init {
        this.message = message
        this.notifier = receiver
    }

    constructor(message: Message) : this(message, null)

    override fun giveBack(message: Message){
        if(notifier != null){
            MessageManager.mediator.giveBack(message, this.notifier)
        }

    }
}