package com.pj.core

abstract class AnyNode : PublishingNode() {

    override fun getReceivingMessages(): List<String> {
        return emptyList()
    }
    override fun onReceive(messageBox: MessageBox){
        this.onAnyMessage(messageBox)
    }

    abstract fun onAnyMessage(messageBox: MessageBox)
}