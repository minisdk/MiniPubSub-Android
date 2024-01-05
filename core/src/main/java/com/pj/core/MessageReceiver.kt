package com.pj.core

interface MessageReceiver{
    fun getReceivingMessages() : List<String>
    fun onReceive(messageBox: MessageBox)
}