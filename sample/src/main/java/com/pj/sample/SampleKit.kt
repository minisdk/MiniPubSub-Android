package com.pj.sample

import android.util.Log
import com.pj.core.MessageHandler
import com.pj.core.Message
import com.pj.core.MessageHolder
import com.pj.core.Tag
import kotlinx.coroutines.handleCoroutineException

class SampleKit {
    private val TAG = SampleKit::class.java.name

    private val handler : MessageHandler = MessageHandler(Tag.native)

    init {
        handler.setHandler("test", this::onTest)
        handler.setHandler("testRecall", this::onTestRecall)
    }

    private fun onTest(messageHolder: MessageHolder){
        Log.d(TAG, "onTest : ${messageHolder.message.data}" )
        handler.notify(Message("native", "this is android message :D"), Tag.game)
    }

    private fun onTestRecall(messageHolder: MessageHolder){
        val message = messageHolder.message
        Log.d(TAG, "onTestReCall : ${message.data}")
        val returned = Message("testReturn", "RECALL [${message.data}]")
        messageHolder.giveBack(returned)
    }
}