package com.minisdk.pubsub.bridge

import android.util.Log
import com.google.gson.Gson
import com.minisdk.pubsub.MessageManager
import com.minisdk.pubsub.Watcher
import com.minisdk.pubsub.data.NodeInfo
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.MessageInfo
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.SdkType

class GameRelay(private val gameCallback : NativeBridgeCallback) {
    private val className = GameRelay::class.java.name
    private val TAG = className
    private val watcher = Watcher(SdkType.Game)
    private val gson = Gson()

    init {
        watcher.watch(this::onReceiveFromNative)
    }
    private fun onReceiveFromNative(message: Message) {
        val info = gson.toJson(message.info)
        gameCallback.onReceive(info, message.payload.json)
    }

    fun send(info: String, json: String){
        val messageInfo = gson.fromJson(info, MessageInfo::class.java)
        if(messageInfo != null){
            val nodeInfo = NodeInfo(messageInfo.nodeInfo.messageOwnerId, watcher.id)
            val message = Message(nodeInfo, messageInfo.topic, messageInfo.replyTopic, Payload(json))
            MessageManager.mediator.broadcast(message)
        }
    }
}