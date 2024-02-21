package com.pj.core.unity

import com.pj.core.MessageCollector
import com.pj.core.proto.NativeBridge.Message
import com.pj.core.MessageHolder
import com.pj.core.Tag

class Game(private val unityCallback : NativeBridgeCallback) {
    private val collector = MessageCollector(Tag.game)

    init {
        collector.setHandler(this::onReceive)
    }
    private fun onReceive(messageHolder: MessageHolder) {
        sendToUnity(messageHolder.message)
    }
    fun send(data : ByteArray){
        collector.notify(this.toMessage(data), Tag.native)
    }
    private fun sendToUnity(message: Message){
        val unityMessage = this.toData(message)
        unityCallback.onReceive(unityMessage)
    }

    private fun toMessage(data: ByteArray) : Message {
        return Message.parseFrom(data)
    }

    private fun toData(message: Message) : ByteArray{
        return message.toByteArray()
    }

}