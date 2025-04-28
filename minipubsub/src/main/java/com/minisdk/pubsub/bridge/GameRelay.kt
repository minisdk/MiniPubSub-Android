package com.minisdk.pubsub.bridge

import android.util.Log
import com.google.gson.Gson
import com.minisdk.pubsub.Handler
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
        val handler = Handler(watcher.id, "", SdkType.Game, this::onHandle)
        MessageManager.mediator.handle(handler.target, handler)
    }
    private fun onReceiveFromNative(message: Message) {
        val info = gson.toJson(message.info)
        gameCallback.onReceive(info, message.payload.json)
    }

    private fun onHandle(message: Message): Payload{
        //TODO: Rare case - call synchronously from native to game
        return Payload("{}")
    }

    fun send(info: String, json: String){
        val messageInfo = gson.fromJson(info, MessageInfo::class.java)
        if(messageInfo != null){
            val nodeInfo = NodeInfo(messageInfo.nodeInfo.messageOwnerId, watcher.id)
            val message = Message(nodeInfo, messageInfo.topic, messageInfo.replyTopic, Payload(json))
            MessageManager.mediator.broadcast(message)
        }
    }

    fun sendSync(info: String, json: String): String{
        val messageInfo = gson.fromJson(info, MessageInfo::class.java)
        if(messageInfo != null){
            val nodeInfo = NodeInfo(messageInfo.nodeInfo.messageOwnerId, watcher.id)
            val message = Message(nodeInfo, messageInfo.topic, messageInfo.replyTopic, Payload(json))
            val resultPayload = MessageManager.mediator.sendSync(message)
            return resultPayload.json
        }
        return "{}"
    }
}