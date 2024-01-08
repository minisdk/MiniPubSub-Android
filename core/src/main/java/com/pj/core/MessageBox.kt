package com.pj.core

interface MessageBox {
    val message : Message
    fun giveBack(message: Message)
}