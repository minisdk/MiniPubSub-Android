package com.pj.core


class MessagePostman(message: Message, notifier: Receivable?) : MessageHolder{
    private val notifier : Receivable?
    override val message: Message

    init {
        this.message = message
        this.notifier = notifier
    }

    constructor(message: Message) : this(message, null)

    override fun giveBack(message: Message){
        if(notifier != null){
            MessageManager.mediator.giveBack(message, this.notifier)
        }

    }
}