package com.pj.core

import com.pj.core.extensions.Message

interface MessageHolder {

    val message : Message
    fun giveBack(message: Message)
}