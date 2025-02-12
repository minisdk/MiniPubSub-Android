package com.minisdk.pubsub

import com.minisdk.pubsub.data.IdCounter
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.NodeInfo
import com.minisdk.pubsub.data.Request
import com.minisdk.pubsub.data.ResponseInfo

open class Publisher : Node() {
    
    private companion object{
        val idCounter = IdCounter()
    }

    fun publish(key: String, message: Message){
        val nodeInfo = NodeInfo(id, id)
        val request = Request(nodeInfo, key, message.json, "")
        MessageManager.mediator.broadcast(request)
    }
    
    fun publish(key: String, message: Message, responseCallback: ReceiveDelegate){
        val responseKey = "${key}_id${idCounter.getNext()}"
        // Register instant receiver
        val receiver = Receiver(-1, responseKey, responseCallback)
        MessageManager.mediator.registerInstantReceiver(receiver)
        // Broadcast request
        val nodeInfo = NodeInfo(id, id)
        val request = Request(nodeInfo, key, message.json, responseKey)
        MessageManager.mediator.broadcast(request)
    }

    fun respond(responseInfo: ResponseInfo, message: Message){
        val nodeInfo = NodeInfo(id, id)
        val request = Request(nodeInfo, responseInfo.key, message.json, "")
        MessageManager.mediator.broadcast(request)
    }
}
