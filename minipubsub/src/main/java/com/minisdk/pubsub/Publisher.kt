package com.minisdk.pubsub

import com.minisdk.pubsub.data.IdCounter
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.NodeInfo
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.MessageInfo
import com.minisdk.pubsub.data.SdkType
import com.minisdk.pubsub.data.Topic
import com.minisdk.pubsub.data.defaultTopic

open class Publisher : Node() {
    
    private companion object{
        val idCounter = IdCounter()
    }

    fun publish(topic: Topic, payload: Payload){
        val nodeInfo = NodeInfo(id, id)
        val message = Message(nodeInfo, topic, defaultTopic, payload)
        MessageManager.mediator.broadcast(message)
    }
    
    fun publish(topic: Topic, payload: Payload, replyCallback: ReceiveDelegate){
        val replyKey = "${topic.key}_id${idCounter.getNext()}"
        val replyTopic = Topic(replyKey, SdkType.Native)
        // Register instant receiver
        val receiver = Receiver(-1, replyKey, SdkType.Native, replyCallback)
        MessageManager.mediator.registerInstantReceiver(receiver)
        // Broadcast message
        val nodeInfo = NodeInfo(id, id)
        val message = Message(nodeInfo, topic, replyTopic, payload)
        MessageManager.mediator.broadcast(message)
    }

    fun reply(receivedMessageInfo: MessageInfo, payload: Payload){
        this.publish(receivedMessageInfo.replyTopic, payload)
    }

    fun sendSync(topic: Topic, payload: Payload){
        val nodeInfo = NodeInfo(id, id)
        val message = Message(nodeInfo, topic, defaultTopic, payload)
        MessageManager.mediator.sendSync(message)
    }
}
