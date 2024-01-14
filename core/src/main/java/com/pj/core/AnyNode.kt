package com.pj.core

abstract class AnyNode : PublishingNode() {

    override fun getReceivingMessageTypes(): List<String>? {
        return null
    }
    override fun onReceive(messageHolder: MessageHolder){
        this.onReceiveAny(messageHolder)
    }

    abstract fun onReceiveAny(messageHolder: MessageHolder)
}