package com.pj.core.unity

import com.pj.core.Event
import com.pj.core.EventComponent

class UnityEventComponent(private val unityCallback : NativeBridgeCallback) : EventComponent() {
    override fun onInitialize(): Map<String, (Event) -> Unit> {
        return mapOf(
            Pair(RECEIVE_ANY, ::sendToUnity)
        )
    }

    fun send(message : String){
        this.notify(this.toEvent(message))
    }

    private fun sendToUnity(event: Event){
        val message = this.toMessage(event)
        unityCallback.onReceive(message)
    }

    private fun toEvent(message: String) : Event{
        val split = message.split('|')
        return Event(split[0],this,split[1])
    }

    private fun toMessage(event: Event) : String{
        return "${event.type}|${event.message}";
    }
}