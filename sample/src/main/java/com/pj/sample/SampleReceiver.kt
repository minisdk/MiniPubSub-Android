package com.pj.sample

import android.util.Log
import com.pj.core.FilterNode
import com.pj.core.Message
import com.pj.core.MessageHolder

class SampleReceiver : FilterNode() {
    private val TAG = SampleReceiver::class.java.name

    override fun onInitialize(): Map<String, (MessageHolder) -> Unit> {
        return mapOf(
            Pair("test", this::onTest),
            Pair("testRecall", this::onTestRecall)
        )
    }

    private fun onTest(messageHolder: MessageHolder){
        Log.d(TAG, "onTest : ${messageHolder.message.data}" )
    }

    private fun onTestRecall(messageHolder: MessageHolder){
        val message = messageHolder.message
        Log.d(TAG, "onTestReCall : ${message.data}")
        val returned = Message("testReturn", "RECALL [${message.data}]")
        messageHolder.giveBack(returned)
    }
}