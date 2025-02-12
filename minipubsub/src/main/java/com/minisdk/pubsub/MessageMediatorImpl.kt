package com.minisdk.pubsub

import android.util.Log
import com.google.gson.Gson
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Request

internal class MessageMediatorImpl : MessageMediator {
    private val TAG = MessageMediator::class.java.name
    private val watcherKey = "Key_Watcher_Reserved"

    private val receiversMap: MutableMap<String, MutableList<Receiver>> = mutableMapOf()
    private val instantReceiverMap : MutableMap<String, Receiver> = mutableMapOf()

    override fun register(receiver: Receiver) {
        val receivers = receiversMap.getOrPut(receiver.key){
            mutableListOf()
        }
        receivers.add(receiver)
    }

    override fun unregister(id: Int, key: String) {
        val receivers = receiversMap[key]
        receivers?.removeIf{receiver ->
            receiver.nodeId == id
        }
    }

    override fun registerInstantReceiver(receiver: Receiver) {
        instantReceiverMap[receiver.key] = receiver
    }

    override fun broadcast(request: Request) {
        instantReceiverMap.remove(request.key)?.delegate?.invoke(request)

        receiversMap[request.key]?.forEach { receiver ->
            if(receiver.nodeId != request.info.nodeInfo.publisherId)
            {
                receiver.delegate.invoke(request)
            }
        }

        receiversMap[watcherKey]?.forEach {watcher ->
            if(watcher.nodeId != request.info.nodeInfo.publisherId)
            {
                watcher.delegate.invoke(request)
            }
        }
    }

}