package com.pj.core

class MessageHandler : MessageNode(){

    private val handlerMap : MutableMap<String, (MessageHolder) -> Unit> = mutableMapOf()

    init {
        MessageManager.mediator.register(this)
    }

    override fun onReceive(messageHolder: MessageHolder){
        val handler = handlerMap[messageHolder.message.type]
        handler?.invoke(messageHolder)
    }

    fun setHandler(messageType: String, handler : (MessageHolder) -> Unit){
        handlerMap[messageType] = handler
        MessageManager.mediator.registerType(this, messageType)
    }
}