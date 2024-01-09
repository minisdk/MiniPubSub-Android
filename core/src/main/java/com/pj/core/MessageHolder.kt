package com.pj.core

interface MessageHolder {
    val message : Message
    fun giveBack(message: Message)
}