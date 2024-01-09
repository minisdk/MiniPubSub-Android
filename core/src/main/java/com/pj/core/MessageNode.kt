package com.pj.core

interface MessageNode{
    fun notify(message : Message)
    fun notify(message: Message, target: MessageNode)
    fun getReceivingMessages() : List<String>
    fun onReceive(messageHolder: MessageHolder)
}