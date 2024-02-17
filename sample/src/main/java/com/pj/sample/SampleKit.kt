package com.pj.sample

import android.util.Log
import com.pj.core.MessageHandler
import com.pj.core.extensions.Message
import com.pj.core.MessageHolder
import com.pj.core.Tag
import com.pj.core.extensions.ContainerBuilder
import com.pj.core.extensions.add
import com.pj.core.extensions.getString

class SampleKit {
    private val TAG = SampleKit::class.java.name

    private val handler : MessageHandler = MessageHandler(Tag.native)

    init {
        handler.apply {  }
        handler.setHandler("test", this::onTest)
        handler.setHandler("testRecall", this::onTestRecall)
    }

    private fun onTest(messageHolder: MessageHolder){
        val data = messageHolder.message.container.getString("data")
        Log.d(TAG, "onTest : $data" )

        val containerBuilder = ContainerBuilder()
        containerBuilder.add("data", "this is android message :D")
        val message = Message("native", containerBuilder.build())
        handler.notify(message, Tag.game)
    }

    private fun onTestRecall(messageHolder: MessageHolder){
        val data = messageHolder.message.container.getString("data")
        Log.d(TAG, "onTestReCall : $data")

        val containerBuilder = ContainerBuilder()
        containerBuilder.add("data", "RECALL [$data]")
        val returned = Message("testReturn", containerBuilder.build())
        messageHolder.giveBack(returned)
    }
}