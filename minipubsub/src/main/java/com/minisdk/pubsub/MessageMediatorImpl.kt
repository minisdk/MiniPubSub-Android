package com.minisdk.pubsub

import com.minisdk.pubsub.data.Message
import com.minisdk.pubsub.data.Payload
import com.minisdk.pubsub.data.SdkType

internal class MessageMediatorImpl : MessageMediator {
    private val TAG = MessageMediator::class.java.name
    private val watcherKey = "Key_Watcher_Reserved"

    private val receiversMap: MutableMap<String, MutableList<Receiver>> = mutableMapOf()
    private val instantReceiverMap : MutableMap<String, Receiver> = mutableMapOf()
    private val handlerMap: MutableMap<String, Handler> = mutableMapOf()
    private val targetHandlerMap: MutableMap<SdkType, Handler> = mutableMapOf()

    override fun register(receiver: Receiver) {
        val receivers = receiversMap.getOrPut(receiver.key){
            mutableListOf()
        }
        receivers.add(receiver)
    }

    override fun unregister(id: Int, key: String) {
        val receivers = receiversMap[key]
        receivers?.removeAll{receiver ->
            receiver.nodeId == id
        }
    }

    override fun registerInstantReceiver(receiver: Receiver) {
        instantReceiverMap[receiver.key] = receiver
    }

    override fun broadcast(message: Message) {
        instantReceiverMap.remove(message.key)?.delegate?.invoke(message)

        receiversMap[message.key]?.forEach { receiver ->
            if(receiver.canInvoke(message.info))
            {
                receiver.delegate.invoke(message)
            }
        }

        receiversMap[watcherKey]?.forEach {watcher ->
            if(watcher.canInvoke(message.info))
            {
                watcher.delegate.invoke(message)
            }
        }
    }

    override fun handle(key: String, handler: Handler) {
        handlerMap[key] = handler
    }

    override fun handle(target: SdkType, handler: Handler) {
        targetHandlerMap[target] = handler
    }

    override fun sendSync(message: Message): Payload {
        val keyHandler = handlerMap[message.key]
        if(keyHandler != null && keyHandler.canInvoke(message.info)){
            return keyHandler.handleDelegate.invoke(message)
        }
        val targetHandler = targetHandlerMap[message.info.topic.target]
        if(targetHandler != null && targetHandler.canInvoke(message.info)){
            return targetHandler.handleDelegate.invoke(message)
        }
        return Payload("{}")
    }

}