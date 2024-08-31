package com.minisdk.pubsub

class MessageManager {

    companion object{
        @JvmStatic
        private val instance : MessageManager by lazy { MessageManager() }
        val mediator : MessageMediator get() = instance.mediator
    }

    val mediator : MessageMediator by lazy { MessageMediatorImpl() }

}