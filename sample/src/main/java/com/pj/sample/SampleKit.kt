package com.pj.sample

import android.util.Log
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.module.ModuleBase

data class ToastData(val toastMessage: String, val toastDuration: Int)
data class ToastResult(val toastCount: Int)

class SampleKit : ModuleBase {
    private val TAG = SampleKit::class.java.name

    private val messenger : Messenger = Messenger()
    private var count: Int = 0

    init {
        Log.d(TAG, "[pubsubtest] SampleKit init")
        messenger.subscribe("test", this::onTest)
        messenger.subscribe("SEND_TOAST", this::onToast)
    }

    override fun getName(): String {
        return SampleKit::class.java.name
    }

    private fun onTest(message: Message){
        Log.d(TAG, "[pubsubtest] onTest key : ${message.key} data : ${message.data}" )
        val result = Message("native", "data from android :D")
        messenger.publish(result)
    }

    private fun onToast(message: Message){
        Log.d(TAG, "[pubsubtest] onToast key : ${message.key} data : ${message.data}" )
        Log.d(TAG, "[pubsubtest] ToastData from unreal : ${message.data<ToastData>().toastMessage},   duration : ${message.data<ToastData>().toastDuration}" )
        count++
        val result = Message("SEND_TOAST_RESULT", ToastResult(count))
        messenger.publish(result);
    }
}