package com.minisdk.pubsub.data

import com.google.gson.Gson

data class Message(
    val json: String){

    constructor(data: Any) : this(Gson().toJson(data))

    inline fun <reified T> data() : T{
        return Gson().fromJson(this.json, T::class.java)
    }
}