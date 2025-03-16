package com.minisdk.pubsub.data

import com.google.gson.Gson

data class NodeInfo(
    val messageOwnerId: Int,
    val publisherId: Int,
)

data class MessageInfo(
    val nodeInfo: NodeInfo,
    val key: String,
    val replyKey: String
)

data class Message(val info: MessageInfo, val payload: Payload){
    val key : String
        get() = info.key

    inline fun <reified T> data() : T{
        return Gson().fromJson(this.payload.json, T::class.java)
    }

    constructor(nodeInfo: NodeInfo, key: String, payload: Payload, replyKey: String) : this(
        MessageInfo(nodeInfo, key, replyKey), payload
    )
}
