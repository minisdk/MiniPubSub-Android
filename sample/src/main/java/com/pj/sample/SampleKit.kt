package com.pj.sample

import android.util.Log
import com.pj.pubsub.Messenger
import com.pj.pubsub.data.Message

class SampleKit {
    private val TAG = SampleKit::class.java.name

    private val messenger : Messenger = Messenger()

    init {
        Log.d(TAG, "[pubsubtest] SampleKit init")
        messenger.subscribe("test", this::onTest)
    }

    private fun onTest(message: Message){
        Log.d(TAG, "[pubsubtest] onTest key : ${message.key} data : ${message.data}" )
        val result = Message("native", "data from android :D")
        messenger.publish(result)
    }
}