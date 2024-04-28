package com.pj.pubsub

import com.pj.pubsub.extensions.Message

class Messenger : ReceivablePublisher(){

    private var allTag: Tag = Tag.none
    private val handlerMap : MutableMap<String, (Message) -> Unit> = mutableMapOf()
    private val handlerList : MutableList<(Message)->Unit> = mutableListOf()

    init {
        MessageManager.mediator.register(this)
    }

    override fun setReceivingRule(all: Tag) {
        allTag = all
    }

    override fun matchTag(tag: Tag): Boolean {
        return tag.contains(allTag)
    }

    override fun onReceive(envelopeHolder: EnvelopeHolder){
        val envelope = envelopeHolder.envelope
        val handler = handlerMap[envelope.message.key]
        handler?.invoke(envelope.message)

        handlerList.forEach { conditionHandler ->
            conditionHandler.invoke(envelope.message)
        }
    }

    fun subscribe(key: String, handler : (Message) -> Unit){
        handlerMap[key] = handler
    }

    fun unsubscribe(key: String){
        handlerMap.remove(key);
    }

    fun subscribe(handler: (Message) -> Unit){
        handlerList.add(handler)
    }

    fun unsubscribe(handler: (Message) -> Unit){
        handlerList.removeIf{ conditionHandler ->
            conditionHandler == handler
        }
    }
}