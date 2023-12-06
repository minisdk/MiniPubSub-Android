package com.pj.core

internal class EventMediatorImpl : EventMediator {

    private val requestTypeMap : MutableMap<String, MutableList<EventComponent>> by lazy { mutableMapOf() }

    override fun add(component: EventComponent) {
        val eventType = component.eventMap.keys
        for (type in eventType){
            val list = requestTypeMap.getOrPut(type) { mutableListOf() }
            list.add(component)
        }
    }

    override fun add(component: EventComponent, eventType: String) {
        val list = requestTypeMap.getOrPut(eventType) { mutableListOf() }
        if(!list.contains(component))
            list.add(component)
    }

    override fun notify(event: Event) {
        val components = requestTypeMap.getOrDefault(event.type, null)
        components?.forEach { component -> component.onReceive(event) }
    }

    override fun notify(event: Event, target: EventComponent){
        target.onReceive(event)
    }
}