package com.minisdk.pubsub

import com.minisdk.pubsub.data.IdCounter
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.NodeInfo
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.MessageInfo

open class Publisher : Node() {
    
    private companion object{
        val idCounter = IdCounter()
    }

    fun publish(key: String, payload: Payload){
        val nodeInfo = NodeInfo(id, id)
        val message = Message(nodeInfo, key, payload, "")
        MessageManager.mediator.broadcast(message)
    }
    
    fun publish(key: String, payload: Payload, replyCallback: ReceiveDelegate){
        val replyKey = "${key}_id${idCounter.getNext()}"
        // Register instant receiver
        val receiver = Receiver(-1, replyKey, replyCallback)
        MessageManager.mediator.registerInstantReceiver(receiver)
        // Broadcast message
        val nodeInfo = NodeInfo(id, id)
        val message = Message(nodeInfo, key, payload, replyKey)
        MessageManager.mediator.broadcast(message)
    }

    fun reply(receivedMessageInfo: MessageInfo, payload: Payload) : Boolean{
        if(receivedMessageInfo.replyKey.isEmpty())
            return false
        val nodeInfo = NodeInfo(id, id)
        val message = Message(nodeInfo, receivedMessageInfo.replyKey, payload, "")
        MessageManager.mediator.broadcast(message)
        return true
    }
}
