package com.pj.core

interface EventMediator {
    fun add(component: EventComponent)
    fun add(component: EventComponent, eventType: String)
    fun notify(event: Event)
    fun notify(event: Event, target: EventComponent)
}