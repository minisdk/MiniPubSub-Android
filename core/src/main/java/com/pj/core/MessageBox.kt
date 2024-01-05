package com.pj.core

class MessageBox(message: Message, receiver: MessageNotifier?) {

    private val notifier : MessageNotifier?
    val message : Message
    val returnable : Boolean
        get() {
            return notifier != null
        }

    init {
        this.message = message
        this.notifier = receiver
    }

    fun giveBack(message: Message){
        if(notifier != null){
            MessageManager.mediator.giveBack(message, this.notifier)
        }

    }
}