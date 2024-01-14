package com.pj.core

abstract class FilterNode : PublishingNode(){

    private val handlerMap : MutableMap<String, (MessageHolder) -> Unit> by lazy { onInitialize().toMutableMap() }

    protected abstract fun onInitialize() : Map<String, (MessageHolder) -> Unit>

    override fun onReceive(messageHolder: MessageHolder){
        val handler = handlerMap[messageHolder.message.type]
        handler?.invoke(messageHolder)
    }

    override fun getReceivingMessageTypes() : List<String>{
        return handlerMap.keys.toList()
    }

    fun addMessageHandler(messageType: String, handler : (MessageHolder) -> Unit){
        handlerMap[messageType] = handler
        MessageManager.mediator.add(this, messageType)
    }
}