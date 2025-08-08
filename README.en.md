# MiniPubSub-Android
Helps comunication between Android and game(Unity and Unreal Engine)

## Getting started
Download minipubsub aar in [Release](https://github.com/minisdk/MiniPubSub-Android/releases).
and place it in your module project.

Add aar to your android module project.
Add Gson to gradle
```groovy
dependencies{
    // other dependencies
    implementation("com.google.code.gson:gson:2.11.0")
}
```

## How to use

### Payload
Data storing unit.<br>
Stores data as json serialized string.<br>
Stored data must be able to be serialized to json.

### Topic
Message Identifier.<br>
Identifies message and helps determine the destination of a message.

## MessageInfo
Message Describing Unit.<br>
Describes message's information

### Message
Data deliver unit.
- info : MessageInfo object.
- key : String property, identifier of message. Published messages are delivered to Messenger which registers that key.
- payload : Payload object
- data<T> : Stored data of Generic type T

### Messenger
publish and subscribe messages.
- Publishes message to other android or game side Messenger. 
- Subscribes message from other android or game side MessageHandler object.
- Sends message syncronously to other android or game side Messenger.
- Handles message from other android or game side MessageHandler object and return syncronously

### Usage Sample
```kotlin
class MyController{
    private const val KEY_SUB_HELLO = "SAMPLE::HELLO"
    private const val KEY_PUB_WORLD = "SAMPLE::WORLD"
    private const val KEY_SUB_HELLO_REPLY_MODE = "SAMPLE::HELLO_REPLY"
    private const val KEY_SYNC_SEND = "SAMPLE::SYNCSEND"
    private val messenger = Messenger()

    init {
        messenger.subscribe(KEY_SUB_HELLO, ::OnHello)
        messenger.subscribe(KEY_SUB_HELLO_REPLY_MODE, ::OnHelloReplyMode)
        messenger.handle(KEY_SYNC_SEND, ::OnSyncSend)
    }

    private fun OnHello(message: Message){
        val myData = message.data<MyHelloData>
        // Do Something...
        messenger.publish(
            Topic(KEY_PUB_WORLD, SdkType.Game),
            Payload(MyWorldData(/* initialize data members */))
        )
    }

    private fun OnHelloReplyMode(message: Message){
        val myData = message.data<MyHelloData>
        // Do Something...
        messenger.reply(
            message.info,
            Payload(MyWorldData(/* initialize data members */))
        )
    }

    private fun OnSyncSend(message: Message): Payload{
        val mySyncData = message.data<MySyncData>
        // Do Something...
        return Payload(MySyncResult(/* initialize data members */)
    }
}
```
