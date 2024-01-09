package com.pj.core

abstract class AnyNode : PublishingNode() {

    override fun getReceivingMessages(): List<String> {
        return emptyList()
    }
    override fun onReceive(messageHolder: MessageHolder){
        this.onAnyMessage(messageHolder)
    }

    abstract fun onAnyMessage(messageHolder: MessageHolder)
}