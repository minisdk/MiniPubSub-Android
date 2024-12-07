package com.pj.sample

import android.util.Log
import android.widget.Toast
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.bridge.ContextManager
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
        messenger.subscribe("SEND_TOAST", this::onToast)
    }

    override fun getName(): String {
        return SampleKit::class.java.name
    }

    private fun onToast(message: Message){
        val activity = ContextManager.activityContext
        activity?.runOnUiThread {
            val toastData = message.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()

            count++
            val result = Message("SEND_TOAST_RESULT", ToastResult(count))
            messenger.publish(result);
        }
    }
}