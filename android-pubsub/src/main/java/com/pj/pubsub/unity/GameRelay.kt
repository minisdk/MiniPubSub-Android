package com.pj.pubsub.unity

import android.util.Log
import com.pj.pubsub.proto.NativePubSub.Message
import com.pj.pubsub.MessageHolder
import com.pj.pubsub.Messenger
import com.pj.pubsub.Tag

class GameRelay(private val unityCallback : NativeBridgeCallback) {
    private val messenger = Messenger(Tag.game)

    init {
        messenger.subscribe(this::onReceive){_ -> true}
    }
    private fun onReceive(messageHolder: MessageHolder) {
        sendToUnity(messageHolder.message)
    }
    fun send(data : ByteArray){
        messenger.publish(this.toMessage(data), Tag.native)
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