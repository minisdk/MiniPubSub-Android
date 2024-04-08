package com.pj.pubsub.unity

import android.util.Log
import com.pj.pubsub.proto.NativePubSub.Message
import com.pj.pubsub.Channel
import com.pj.pubsub.ChannelConnection
import com.pj.pubsub.Messenger
import com.pj.pubsub.Tag
import com.pj.pubsub.proto.NativePubSub.Envelope

class GameRelay(private val unityCallback : NativeBridgeCallback) {
    private val messenger = Messenger()
    private val className = this.javaClass::class.java.name

    init {
        messenger.setTagRule(Tag.game)
        messenger.subscribe(this::onReceiveFromNative){ _ -> true}
    }
    private fun onReceiveFromNative(channel: Channel) {
        if(channel is ChannelConnection){
            channel.serializeTag()
            val data = toData(channel.envelope)
            if(data != null){
                unityCallback.onReceive(data)
            }
        }
    }
    fun send(data : ByteArray){
        val envelope = toEnvelope(data)
        if(envelope != null){
            val tag = Tag.named(envelope.tagNamesList.toTypedArray())
            messenger.publish(envelope, tag)
        }
        else{
            Log.e(className, "send: protobuf deserialize fail." )
        }
    }

    private fun toEnvelope(data: ByteArray): Envelope {
        return Envelope.parseFrom(data)
    }

    private fun toData(envelope: Envelope): ByteArray{
        return envelope.toByteArray()
    }

    private fun toMessage(data: ByteArray) : Message {
        return Message.parseFrom(data)
    }

    private fun toData(message: Message) : ByteArray{
        return message.toByteArray()
    }

}