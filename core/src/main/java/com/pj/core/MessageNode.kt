package com.pj.core


interface Receivable{
    fun onReceive(messageHolder: MessageHolder)
}

open class Notifier{

    private object IDCounter {
        private var id = 0;
        val ID : Int
            get() {
                return id++
            }
    }
    val id:Int = IDCounter.ID
    fun notify(message : Message){
        MessageManager.mediator.notify(message, this)
    }
    fun notify(message: Message, target: MessageNode){
        MessageManager.mediator.notify(message, this, target)
    }
}
abstract class MessageNode : Notifier(), Receivable{
}