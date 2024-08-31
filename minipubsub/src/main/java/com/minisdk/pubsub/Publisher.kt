package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message

class Publisher : Node{

    private object IDCounter{
        private var id: Int = PublisherType.Android.defaultID
        val ID : Int
            get() {
                return ++IDCounter.id
            }
    }
    override val id = IDCounter.ID

    fun publish(message: Message){
        MessageManager.mediator.publish(message, id)
    }
}
