package com.pj.pubsub.unity

import android.util.Log
import com.google.gson.Gson
import com.pj.pubsub.Watcher
import com.pj.pubsub.data.Message
import com.pj.pubsub.data.NativeBridgeCallback
import kotlin.reflect.typeOf

class GameRelay(private val unityCallback : NativeBridgeCallback) {
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
            unityCallback.onReceiveString(json)
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