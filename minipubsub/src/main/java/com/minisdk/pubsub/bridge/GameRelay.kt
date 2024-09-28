package com.minisdk.pubsub.bridge

import android.util.Log
import com.google.gson.Gson
import com.minisdk.pubsub.Watcher
import com.minisdk.pubsub.data.Message

class GameRelay(private val gameCallback : NativeBridgeCallback) {
    private val className = GameRelay::class.java.name
    private val TAG = className
    private val watcher = Watcher()
    private val gson = Gson()

    init {
        watcher.watch(this::onReceiveFromNative)
    }
    private fun onReceiveFromNative(message: Message) {
        val json = gson.toJson(message)
        if(json != null){
            gameCallback.onReceiveString(json)
        }
    }

    fun send(json: String){
        val message = gson.fromJson(json, Message::class.java)
        if(message != null){
            watcher.publish(message)
        }
        else{
            Log.e(className, "send: protobuf deserialize fail." )
        }
    }
}