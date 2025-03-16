package com.pj.sample

import android.util.Log
import android.widget.Toast
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.bridge.ContextManager
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.Message

data class ToastData(val toastMessage: String, val toastDuration: Int)
data class ToastResult(val toastCount: Int)

object SampleKit {
    private val TAG = SampleKit::class.java.name

    private val messenger : Messenger = Messenger()
    private var count: Int = 0

    init {
        Log.d(TAG, "[pubsubtest] SampleKit init")
    }

    fun prepare(){
        Log.d(TAG, "!!!! SampleKit.prepare: run?")
        messenger.subscribe("SEND_TOAST", this::onToast)
        messenger.subscribe("SEND_TOAST_ASYNC", this::onToastAsync)
    }

    private fun onToast(message: Message){
        val activity = ContextManager.activityContext
        activity?.runOnUiThread {
            val toastData = message.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()

            count++
            val result = Payload(ToastResult(count))
            messenger.publish("SEND_TOAST_RESULT", result);
        }
    }

    private fun onToastAsync(message: Message){
        val activity = ContextManager.activityContext
        Log.d(TAG, "onToastAsync: message : " + message.payload.json)
        activity?.runOnUiThread {
            val toastData = message.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()

            count++
            val result = Payload(ToastResult(count))
            messenger.reply(message.info, result)
        }
    }
}