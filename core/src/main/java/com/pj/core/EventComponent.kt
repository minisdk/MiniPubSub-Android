package com.pj.core

abstract class EventComponent {
    protected companion object{
        val RECEIVE_ANY = ".any"
    }

    private val mediator : EventMediator = EventManager.mediator
    val eventMap : MutableMap<String, (Event) -> Unit> by lazy { onInitialize().toMutableMap() }

    internal fun onReceive(event: Event){
        val receiver = eventMap[event.type]
        receiver?.invoke(event)
        val anyReceiver = eventMap[RECEIVE_ANY]
        anyReceiver?.invoke(event)
    }

    protected abstract fun onInitialize() : Map<String, (Event) -> Unit>

    protected fun addEvent(eventType: String, receiver : (Event) -> Unit){
        eventMap[eventType] = receiver
        mediator.add(this, eventType)
    }

    protected fun notify(event: Event){
        mediator.notify(event)
    }

    protected fun notify(event: Event, target: EventComponent){
        mediator.notify(event, target)
    }

}