package com.pj.core

class MessageManager {

    companion object{
        @JvmStatic
        private val instance : MessageManager by lazy { MessageManager() }
        val mediator : MessageMediator get() = instance.mediator

        fun add(component: MessageReceiver) = instance.mediator.add(component)
    }

    val mediator : MessageMediator by lazy { MessageMediatorImpl() }

}