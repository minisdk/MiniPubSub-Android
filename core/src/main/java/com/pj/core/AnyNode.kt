package com.pj.core

abstract class AnyNode : PublishingNode() {

    override fun getReceivingMessageTypes(): List<String> {
        return emptyList()
    }
    override fun onReceive(messageHolder: MessageHolder){
        this.onReceiveAny(messageHolder)
    }

    abstract fun onReceiveAny(messageHolder: MessageHolder)
}