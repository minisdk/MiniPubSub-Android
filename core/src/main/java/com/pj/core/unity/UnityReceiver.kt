package com.pj.core.unity

import com.pj.core.AnyReceiver
import com.pj.core.FilterReceiver
import com.pj.core.Message
import com.pj.core.MessageBox
import com.pj.core.MessageManager
import com.pj.core.MessageReceiver
import com.pj.core.MessageNotifier

class UnityReceiver(private val unityCallback : NativeBridgeCallback) : AnyReceiver(), MessageNotifier {
    override fun onAnyMessage(messageBox: MessageBox) {
        sendToUnity(messageBox.message)
    }
    override fun notify(message: Message) {
        MessageManager.mediator.notify(message, this)
    }

    override fun notify(message: Message, target: MessageReceiver) {
        MessageManager.mediator.notify(message, this, target)
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