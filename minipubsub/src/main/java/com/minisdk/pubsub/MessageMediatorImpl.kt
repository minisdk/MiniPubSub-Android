package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

internal class MessageMediatorImpl : MessageMediator {
    private val TAG = MessageMediator::class.java.name

    private val receiverDic: MutableMap<String, MutableList<Receiver>> = mutableMapOf()
    private val watcherDic: MutableMap<Int, Receiver> = mutableMapOf()

    override fun register(receiver: Receiver) {
        val receivers = receiverDic.getOrPut(receiver.key){
            mutableListOf()
        }
        receivers.add(receiver)
    }

    override fun unregister(id: Int, key: String) {
        val receivers = receiverDic[key]
        receivers?.removeIf{receiver ->
            receiver.nodeId == id
        }
    }

    override fun watch(receiver: Receiver) {
        watcherDic[receiver.nodeId] = receiver
    }

    override fun unwatch(id: Int) {
        watcherDic.remove(id)
    }

    override fun publish(message: Message, publisherId: Int) {
        val receivers = receiverDic[message.key]
        receivers?.forEach { receiver ->
            if(receiver.nodeId == publisherId)
                return
            receiver.delegate?.invoke(message)
        }

        for (watcher in watcherDic.values){
            if(watcher.nodeId == publisherId)
                continue
            watcher.delegate?.invoke(message)
        }
    }

}