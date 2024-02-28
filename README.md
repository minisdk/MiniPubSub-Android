# AndroidCore
Helps comunication between Android and game(Unity only)

## How to use

### Container
Data storing unit.

Currently storess the following types:
- Boolean
- Int
- Float
- String
- ByteArray
- Other Container object

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
        containerBuilder.add(pressOk, pressOk)
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