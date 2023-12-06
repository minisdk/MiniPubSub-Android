package com.pj.core

class Event (
    public val type: String,
    public val notifier: EventComponent,
    public val message: String){
}