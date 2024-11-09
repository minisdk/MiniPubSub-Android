package com.minisdk.pubsub.bridge

import android.util.Log
import com.google.gson.Gson
import com.minisdk.pubsub.Watcher
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.MessageInfo

class GameRelay(private val gameCallback : NativeBridgeCallback) {
    private val className = GameRelay::class.java.name
    private val TAG = className
    private val watcher = Watcher()
    private val gson = Gson()

    init {
        watcher.watch(this::onReceiveFromNative)
    }
    private fun onReceiveFromNative(message: Message) {
        val info = gson.toJson(message.info)
        gameCallback.onReceive(info, message.data)
    }

    fun send(info: String, data: String){
        val messageInfo = gson.fromJson(info, MessageInfo::class.java)
        if(messageInfo != null){
            watcher.publish(Message(messageInfo, data))
        }
    }
}