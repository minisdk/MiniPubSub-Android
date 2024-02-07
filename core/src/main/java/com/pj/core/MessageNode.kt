package com.pj.core


interface Receivable{
    fun hasKey(key: String): Boolean
    fun onReceive(messageHolder: MessageHolder)
}

open class Notifier(tag: Tag){

    private object IDCounter {
        private var id = 0;
        val ID : Int
            get() {
                return id++
            }
    }
    val id:Int = IDCounter.ID
    val tag: Tag
    init {
        this.tag = tag
    }
    fun notify(message : Message, tag: Tag){
        MessageManager.mediator.notify(message, tag,this)
    }
}
abstract class MessageNode(tag: Tag) : Notifier(tag), Receivable{
}