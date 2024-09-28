package com.pj.sample

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.data.Message

data class ToastResult(val toastCount: Int){
    val toastShowCount = toastCount
}

class SampleKit {
    private val TAG = SampleKit::class.java.name

    private val messenger : Messenger = Messenger()
    private var count: Int = 0

    init {
        Log.d(TAG, "[pubsubtest] SampleKit init")
        messenger.subscribe("test", this::onTest)
        messenger.subscribe("SEND_TOAST", this::onToast)
    }

    private fun onTest(message: Message){
        Log.d(TAG, "[pubsubtest] onTest key : ${message.key} data : ${message.data}" )
        val result = Message("native", "data from android :D")
        messenger.publish(result)
    }

    private fun onToast(message: Message){
        Log.d(TAG, "[pubsubtest] onToast key : ${message.key} data : ${message.data}" )
        count++
        val result = Message("SEND_TOAST_RESULT", ToastResult(count))
        messenger.publish(result);
    }
}