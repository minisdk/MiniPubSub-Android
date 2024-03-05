package com.pj.pubsub

import android.util.Log
import com.pj.pubsub.extensions.Message

class Messenger(tag: Tag) : ReceivablePublisher(tag){

    private val handlerMap : MutableMap<String, (MessageHolder) -> Unit> = mutableMapOf()
    private val conditionHandlers : MutableList<Pair<(MessageHolder)->Unit, (Message)->Boolean>> = mutableListOf()

    init {
        MessageManager.mediator.register(this)
    }

    override fun hasKey(key: String): Boolean {
        return handlerMap.containsKey(key)
    }
    override fun onReceive(messageHolder: MessageHolder){
        val handler = handlerMap[messageHolder.message.key]
        handler?.invoke(messageHolder)

        conditionHandlers.filter { conditionHandler ->
            conditionHandler.second.invoke(messageHolder.message)
        }.forEach { conditionHandler ->
            conditionHandler.first?.invoke(messageHolder)
        }
    }

    fun subscribe(key: String, handler : (MessageHolder) -> Unit){
        handlerMap[key] = handler
    }

    fun unsubscribe(key: String){
        handlerMap.remove(key);
    }

    fun subscribe(handler: (MessageHolder) -> Unit, condition: (Message) -> Boolean ){
        val pair = Pair(handler, condition)
        conditionHandlers.add(pair)
    }
}