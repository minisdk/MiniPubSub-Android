package com.pj.core.unity

import com.pj.core.MessageCollector
import com.pj.core.Message
import com.pj.core.MessageHolder

class Game(private val unityCallback : NativeBridgeCallback) {
    private val collector = MessageCollector()

    init {
        collector.setHandler(this::onReceive)
    }
    private fun onReceive(messageHolder: MessageHolder) {
        sendToUnity(messageHolder.message)
    }
    fun send(message : String){
        collector.notify(this.toEvent(message))
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