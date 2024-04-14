package com.pj.pubsub.unity

import android.util.Log
import com.pj.pubsub.proto.NativePubSub.Message
import com.pj.pubsub.EnvelopeHolder
import com.pj.pubsub.Tag
import com.pj.pubsub.proto.NativePubSub.Envelope

class GameRelay(private val unityCallback : NativeBridgeCallback) {
    private val bridgeMessenger = Bridge()
    private val className = this.javaClass::class.java.name

    init {
        bridgeMessenger.setTagRule(Tag.game)
        bridgeMessenger.subscribe(this::onReceiveFromNative)
    }
    private fun onReceiveFromNative(envelopeHolder: EnvelopeHolder) {
        val envelope = makeEnvelope(envelopeHolder)
        val data = toData(envelope)
        if(data != null){
            unityCallback.onReceive(data)
        }
    }
    fun send(data : ByteArray){
        val envelope = toEnvelope(data)
        if(envelope != null){
            val tag = Tag.named(envelope.tagNamesList.toTypedArray())
            bridgeMessenger.publish(envelope, tag)
        }
        else{
            Log.e(className, "send: protobuf deserialize fail." )
        }
    }

    private fun makeEnvelope(envelopeHolder: EnvelopeHolder): Envelope{
        val builder = Envelope.newBuilder()
        val original = envelopeHolder.envelope

        builder.message = original.message
        builder.senderID = original.senderID
        if(original.hasReceiverID()){
            builder.receiverID = original.receiverID
        }
        builder.addAllTagNames(envelopeHolder.tag.names.toList())

        return builder.build()
    }

    private fun toEnvelope(data: ByteArray): Envelope? {
        return try{
            Envelope.parseFrom(data)
        }
        catch (_: Exception){
            null
        }
    }

    private fun toData(envelope: Envelope): ByteArray?{
        return try{
            envelope.toByteArray()
        }
        catch (_: Exception){
            null
        }
    }

    private fun toMessage(data: ByteArray) : Message {
        return Message.parseFrom(data)
    }

    private fun toData(message: Message) : ByteArray{
        return message.toByteArray()
    }

}