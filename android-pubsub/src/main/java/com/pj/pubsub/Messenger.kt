package com.pj.pubsub

import com.pj.pubsub.extensions.Message

class Messenger : ReceivablePublisher(){

    private var allTag: Tag = Tag.none
    private val handlerMap : MutableMap<String, (Channel) -> Unit> = mutableMapOf()
    private val conditionHandlers : MutableList<Pair<(Channel)->Unit, (Message)->Boolean>> = mutableListOf()

    init {
        MessageManager.mediator.register(this)
    }

    override fun setTagRule(all: Tag) {
        allTag = all
    }

    override fun matchTag(tag: Tag): Boolean {
        return tag.contains(allTag)
    }

    override fun onReceive(channel: Channel){
        val handler = handlerMap[channel.message.key]
        handler?.invoke(channel)

        conditionHandlers.filter { conditionHandler ->
            conditionHandler.second.invoke(channel.message)
        }.forEach { conditionHandler ->
            conditionHandler.first?.invoke(channel)
        }
    }

    fun subscribe(key: String, handler : (Channel) -> Unit){
        handlerMap[key] = handler
    }

    fun unsubscribe(key: String){
        handlerMap.remove(key);
    }

    fun subscribe(handler: (Channel) -> Unit, condition: (Message) -> Boolean ){
        val pair = Pair(handler, condition)
        conditionHandlers.add(pair)
    }

    fun unsubscribe(handler: (Channel) -> Unit){
        conditionHandlers.removeIf{ conditionHandler ->
            conditionHandler.first == handler
        }
    }
}