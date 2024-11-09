package com.minisdk.pubsub.data

import com.google.gson.Gson

data class MessageInfo(
    val key: String
)

data class Message(
    val info: MessageInfo,
    val data: String){

    val key: String
        get() = info.key

    constructor(key: String, data: Any) : this(MessageInfo(key), Gson().toJson(data))

    inline fun <reified T> data() : T{
        return Gson().fromJson(this.data, T::class.java)
    }
}