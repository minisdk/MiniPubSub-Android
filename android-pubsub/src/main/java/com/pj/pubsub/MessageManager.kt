package com.pj.pubsub

class MessageManager {

    companion object{
        @JvmStatic
        private val instance : MessageManager by lazy { MessageManager() }
        val mediator : MessageMediator get() = instance.mediator

        fun add(component: ReceivablePublisher) = instance.mediator.register(component)
    }

    val mediator : MessageMediator by lazy { MessageMediatorImpl() }

}