package com.pj.core

abstract class FilterNode : PublishingNode(){

    private val handlerMap : MutableMap<String, (MessageBox) -> Unit> by lazy { onInitialize().toMutableMap() }

    protected abstract fun onInitialize() : Map<String, (MessageBox) -> Unit>

    override fun onReceive(messageBox: MessageBox){
        val handler = handlerMap[messageBox.message.type]
        handler?.invoke(messageBox)
    }

    override fun getReceivingMessages() : List<String>{
        return handlerMap.keys.toList()
    }

    fun addMessageHandler(messageType: String, handler : (MessageBox) -> Unit){
        handlerMap[messageType] = handler
        MessageManager.mediator.add(this, messageType)
    }
}