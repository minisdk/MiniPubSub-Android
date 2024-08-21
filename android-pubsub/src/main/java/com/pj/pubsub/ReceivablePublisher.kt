package com.pj.pubsub

import android.util.Log
import com.pj.pubsub.data.Message

internal object IDCounter {
    private var id: Int = PublisherType.Android.defaultID
    val ID : Int
        get() {
            return ++id
        }
}

typealias ReceiverDelegate = (Message) -> Unit

data class Receiver(
    val nodeId: Int,
    val key: String,
    val delegate: ReceiverDelegate)

interface Node{
    val id : Int
}

interface Subscribable : Node{
    fun subscribe(key: String, delegate : ReceiverDelegate)
    fun unsubscribe(key: String)
}

interface Watchable : Node{
    fun watch(delegate: ReceiverDelegate)
    fun unwatch()
}

open class Publisher : Node{
    override val id = IDCounter.ID

    fun publish(message: Message){
        MessageManager.mediator.publish(message, id)
    }
}
