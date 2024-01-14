package com.pj.core.unity

import com.pj.core.AnyNode
import com.pj.core.Message
import com.pj.core.MessageHolder

class UnityNode(private val unityCallback : NativeBridgeCallback) : AnyNode() {
    override fun onReceiveAny(messageHolder: MessageHolder) {
        sendToUnity(messageHolder.message)
    }

    fun send(message : String){
        this.notify(this.toEvent(message))
    }
    private fun sendToUnity(message: Message){
        val unityMessage = this.toMessage(message)
        unityCallback.onReceive(unityMessage)
    }

    private fun toEvent(message: String) : Message{
        val split = message.split('|')
        return Message(split[0],split[1])
    }

    private fun toMessage(message: Message) : String{
        return "${message.type}|${message.data}";
    }

}