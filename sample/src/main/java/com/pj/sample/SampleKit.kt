package com.pj.sample

import android.util.Log
import android.widget.Toast
import com.minisdk.pubsub.MessageManager
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.bridge.ContextManager
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Request
import com.minisdk.pubsub.module.ModuleBase

data class ToastData(val toastMessage: String, val toastDuration: Int)
data class ToastResult(val toastCount: Int)

class SampleKit : ModuleBase {
    private val TAG = SampleKit::class.java.name

    private val messenger : Messenger = Messenger()
    private var count: Int = 0

    init {
        Log.d(TAG, "[pubsubtest] SampleKit init")
        messenger.subscribe("SEND_TOAST", this::onToast)
        messenger.subscribe("SEND_TOAST_ASYNC", this::onToastAsync)
    }

    override fun getName(): String {
        return SampleKit::class.java.name
    }

    private fun onToast(request: Request){
        val activity = ContextManager.activityContext
        activity?.runOnUiThread {
            val toastData = request.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()

            count++
            val result = Message(ToastResult(count))
            messenger.publish("SEND_TOAST_RESULT", result);
        }
    }

    private fun onToastAsync(request: Request){
        val activity = ContextManager.activityContext
        Log.d(TAG, "onToastAsync: request : " + request.json)
        activity?.runOnUiThread {
            val toastData = request.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()

            count++
            val result = Message(ToastResult(count))
            val responseInfo = request.getResponseInfo()
            messenger.respond(responseInfo, result)
        }
    }
}