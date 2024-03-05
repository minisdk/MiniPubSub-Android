package com.pj.pubsub

import com.pj.pubsub.extensions.Message

interface Receivable{
    fun hasKey(key: String): Boolean
    fun onReceive(messageHolder: MessageHolder)
}

open class Publisher(tag: Tag){

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
    fun publish(message : Message, tag: Tag){
        MessageManager.mediator.publish(message, tag,this)
    }
}
abstract class ReceivablePublisher(tag: Tag) : Publisher(tag), Receivable{
}