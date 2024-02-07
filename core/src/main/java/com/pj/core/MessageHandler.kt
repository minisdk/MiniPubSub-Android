package com.pj.core


class MessageHandler(tag: Tag) : MessageNode(tag){

    private val handlerMap : MutableMap<String, (MessageHolder) -> Unit> = mutableMapOf()

    init {
        MessageManager.mediator.register(this)
    }

    override fun hasKey(key: String): Boolean {
        return handlerMap.containsKey(key)
    }
    override fun onReceive(messageHolder: MessageHolder){
        val handler = handlerMap[messageHolder.message.key]
        handler?.invoke(messageHolder)
    }

    fun setHandler(key: String, handler : (MessageHolder) -> Unit){
        handlerMap[key] = handler
    }
}