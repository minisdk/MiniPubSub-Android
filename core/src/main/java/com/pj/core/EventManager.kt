package com.pj.core

class EventManager {

    companion object{
        @JvmStatic
        private val instance : EventManager by lazy { EventManager() }
        val mediator : EventMediator get() = instance.mediator

        fun add(component: EventComponent) = instance.mediator.add(component)
    }

    val mediator : EventMediator by lazy { EventMediatorImpl() }

}