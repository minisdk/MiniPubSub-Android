package com.minisdk.pubsub.bridge

import com.google.gson.Gson
import com.minisdk.pubsub.MessageManager
import com.minisdk.pubsub.Watcher
import com.minisdk.pubsub.data.NodeInfo
import com.minisdk.pubsub.data.Request
import com.minisdk.pubsub.data.RequestInfo

class GameRelay(private val gameCallback : NativeBridgeCallback) {
    private val className = GameRelay::class.java.name
    private val TAG = className
    private val watcher = Watcher()
    private val gson = Gson()

    init {
        watcher.watch(this::onReceiveFromNative)
    }
    private fun onReceiveFromNative(request: Request) {
        val info = gson.toJson(request.info)
        gameCallback.onReceive(info, request.json)
    }

    fun send(info: String, json: String){
        val requestInfo = gson.fromJson(info, RequestInfo::class.java)
        if(requestInfo != null){
            val nodeInfo = NodeInfo(requestInfo.nodeInfo.requestOwnerId, watcher.id)
            val request = Request(nodeInfo, requestInfo.key, json, requestInfo.responseKey)
            MessageManager.mediator.broadcast(request)
        }
    }
}