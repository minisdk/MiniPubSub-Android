package com.pj.core

class MessageCollector : MessageNode() {

    private lateinit var handler: (MessageHolder) -> Unit
    init {
        MessageManager.mediator.register(this)
        MessageManager.mediator.registerType(this, "$.ReceiveAny")
    }
    override fun onReceive(messageHolder: MessageHolder){
        if(::handler.isInitialized)
            this.handler(messageHolder)
    }

    fun setHandler(handler: (MessageHolder) -> Unit){
        this.handler = handler
    }

}