package com.minisdk.pubsub

import com.minisdk.pubsub.data.IdCounter

abstract class Node{
    companion object{
        private val idCounter = IdCounter()
    }
    val id : Int = idCounter.getNext()
}
