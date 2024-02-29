# AndroidCore
Helps comunication between Android and game(Unity only)

## Getting started
Download androidBridgeCore aar in [Release](https://github.com/psmjazz/NativeBridge-Android/releases).
and place it in your module project.

Add aar to your android module project. In your module level build.gradle
```groovy
compileOnly(files("[relative path of aar in your project]"))
```

## How to use

### Container
Data storing unit.

Currently storess the following types:
|types|c#|kotlin|swift|
|---|---|---|---|
|boolean|bool|Boolean|Bool|
|32bit-integer|int|Int|Int32|
|float|float|Float|Float|
|string|string|String|String|
|byte array|byte[]|ByteArray|Data|
|other Container object|Container|Conatiner|Conatiner|

### Message
Data deliver unit.
- key : string value, identifier of message. Notified messages are deliverd to handler registerd with key.
- container : Container object

### Tag
Filters message
- MessageHandler sets Tags. It only receives message containing all set tags.
- Notifies message with Tags. messages are arrived messageHandlers which have tags all.

### MessageHandler
Notify and subscribe messages.
- Notifies message to other android or game side MessageHandler object. 
- Subscribes message from other android or game side MessageHandler object.

### Usage
Sample Code Receiving open native alert request from game.
```java

class NativeUIController{
    private val handler = MessageHandler(Tag.Native)

    init {
        // Set handler with key
        handler.setHandler("OPEN_ALRET", onReceive)
    }

    private fun onReceive(messageHolder : MessageHolder){
        // Get data from container
        val alertMessage = messageHolder.message.container.getString("alertMessage")
        val pressOk = openAlert(alertMessage)

        // Create ContainerBuilder and set data.
        val containerBuilder = ContainerBuilder()
        containerBuilder.add("pressOk", pressOk)
        val message = Message("ALERT_RESULT", containerBuilder.build())
        
        // Give back message to message notifier
        messageHolder.giveBack(message)
    }

    private openAlert(message: String): Boolean{
        // Alert open code...
        return true
    }

}
```