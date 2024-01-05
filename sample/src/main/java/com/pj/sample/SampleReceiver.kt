package com.pj.sample

import android.util.Log
import com.pj.core.FilterReceiver
import com.pj.core.Message
import com.pj.core.MessageBox

class SampleReceiver : FilterReceiver() {
    private val TAG = SampleReceiver::class.java.name

    override fun onInitialize(): Map<String, (MessageBox) -> Unit> {
        return mapOf(
            Pair("test", this::onTest),
            Pair("testRecall", this::onTestRecall)
        )
    }

    private fun onTest(messageBox: MessageBox){
        Log.d(TAG, "onTest : ${messageBox.message.data}" )
    }

    private fun onTestRecall(messageBox: MessageBox){
        val message = messageBox.message
        Log.d(TAG, "onTestReCall : ${message.data}")
        val returned = Message("testReturn", "RECALL [${message.data}]")
        messageBox.giveBack(returned)
    }
}