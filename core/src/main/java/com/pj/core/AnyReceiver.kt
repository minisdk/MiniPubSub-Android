package com.pj.core

abstract class AnyReceiver : MessageReceiver {

    override fun getReceivingMessages(): List<String> {
        return emptyList()
    }
    override fun onReceive(messageBox: MessageBox){
        this.onAnyMessage(messageBox)
    }

    abstract fun onAnyMessage(messageBox: MessageBox)
}