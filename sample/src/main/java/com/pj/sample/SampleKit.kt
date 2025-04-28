package com.pj.sample

import android.util.Log
import android.widget.Toast
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.bridge.ContextManager
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.SdkType
import com.minisdk.pubsub.data.Topic

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
        messenger.handle("SEND_TOAST_SYNC", this::onToastSync)
    }

    private fun onToast(message: Message){
        val activity = ContextManager.activityContext
        activity?.runOnUiThread {
            val toastData = message.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()

            count++
            val result = Payload(ToastResult(count))
            messenger.publish(Topic("SEND_TOAST_RESULT", SdkType.Game), result);
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

    private fun onToastSync(message: Message): Payload{
        val activity = ContextManager.activityContext
        Log.d(TAG, "onToastAsync: message : " + message.payload.json)
        activity?.runOnUiThread {
            val toastData = message.data<ToastData>()
            Toast.makeText(activity, toastData.toastMessage, toastData.toastDuration).show()
        }
        count++
        return Payload(ToastResult(count))
    }
}