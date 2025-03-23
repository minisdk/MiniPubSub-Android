package com.minisdk.pubsub

import android.util.Log
import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.MessageInfo
import com.minisdk.pubsub.data.SdkType

typealias ReceiveDelegate = (Message) -> Unit
data class Receiver(
    val nodeId: Int,
    val key: String,
    val target: SdkType,
    val delegate: ReceiveDelegate){

    fun canInvoke(info: MessageInfo): Boolean{
        return nodeId != info.nodeInfo.publisherId && target == info.topic.target;
    }
}