package com.pj.core

class MessageCollector(tag: Tag) : MessageNode(tag) {

    private lateinit var handler: (MessageHolder) -> Unit
    init {
        MessageManager.mediator.register(this)
    }

    override fun hasKey(key: String): Boolean {
        return true
    }

    override fun onReceive(messageHolder: MessageHolder){
        if(::handler.isInitialized)
            this.handler(messageHolder)
    }

    fun setHandler(handler: (MessageHolder) -> Unit){
        this.handler = handler
    }

}