package com.minisdk.pubsub.data

import com.google.gson.Gson

data class NodeInfo(
    val messageOwnerId: Int,
    val publisherId: Int,
)

data class MessageInfo(
    val nodeInfo: NodeInfo,
    val topic: Topic,
    val replyTopic: Topic
)

data class Message(val info: MessageInfo, val payload: Payload){
    val key : String
        get() = info.topic.key

    inline fun <reified T> data() : T{
        return Gson().fromJson(this.payload.json, T::class.java)
    }

    constructor(nodeInfo: NodeInfo, topic: Topic, replyTopic: Topic, payload: Payload) : this(
        MessageInfo(nodeInfo, topic, replyTopic), payload
    )
}
