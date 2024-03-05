package com.pj.sample

import android.util.Log
import com.pj.pubsub.Messenger
import com.pj.pubsub.extensions.Message
import com.pj.pubsub.MessageHolder
import com.pj.pubsub.Tag
import com.pj.pubsub.extensions.ContainerBuilder
import com.pj.pubsub.extensions.add
import com.pj.pubsub.extensions.getString

class SampleKit {
    private val TAG = SampleKit::class.java.name

    private val handler : Messenger = Messenger(Tag.native)

    init {
        handler.apply {  }
        handler.subscribe("test", this::onTest)
        handler.subscribe("testRecall", this::onTestRecall)
    }

    private fun onTest(messageHolder: MessageHolder){
        val data = messageHolder.message.container.getString("data")
        Log.d(TAG, "onTest : $data" )

        val containerBuilder = ContainerBuilder()
        containerBuilder.add("data", "this is android message :D")
        val message = Message("native", containerBuilder.build())
        handler.publish(message, Tag.game)
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