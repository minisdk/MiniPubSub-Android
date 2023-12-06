package com.pj.sample

import android.util.Log
import com.pj.core.Event
import com.pj.core.EventComponent

class SampleComponent : EventComponent() {
    private val TAG = SampleComponent::class.java.name

    override fun onInitialize(): Map<String, (Event) -> Unit> {
        return mapOf(
            Pair("test", this::onTest),
            Pair("testRecall", this::onTestRecall)
        )
    }

    private fun onTest(event :Event){
        Log.d(TAG, "onTest : ${event.message}" )
    }

    private fun onTestRecall(event: Event){
        Log.d(TAG, "onTestReCall : ${event.message}")
        val returned = Event("testReturn", this, "RECALL [${event.message}]")
        this.notify(returned, event.notifier)
    }
}