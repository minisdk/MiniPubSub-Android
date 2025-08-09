package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.MessageInfo
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.SdkType


typealias HandleDelegate = (Message) -> Payload
class Handler(
    val nodeId: Int,
    val key: String,
    val target: SdkType,
    val handleDelegate: HandleDelegate) {

    fun canInvoke(info: MessageInfo): Boolean
    {
        return nodeId != info.nodeInfo.publisherId && target == info.topic.target
    }
}