package com.minisdk.pubsub.data

import com.google.gson.Gson

data class NodeInfo(
    val requestOwnerId: Int,
    val publisherId: Int,
)

data class RequestInfo(
    val nodeInfo: NodeInfo,
    val key: String,
    val responseKey: String
){
    val isResponsible : Boolean
        get() = responseKey.isNotEmpty()

}

data class ResponseInfo(
    val key: String,
)
data class Request(val info: RequestInfo, val json: String){
    val key : String
        get() = info.key

    inline fun <reified T> data() : T{
        return Gson().fromJson(this.json, T::class.java)
    }

    constructor(nodeInfo: NodeInfo, key: String, json: String, responseKey: String) : this(
        RequestInfo(nodeInfo, key, responseKey),
        json
    )

    fun getResponseInfo() : ResponseInfo{
        return ResponseInfo(info.responseKey)
    }
}
