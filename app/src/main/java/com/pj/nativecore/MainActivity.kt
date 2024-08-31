package com.pj.nativecore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.minisdk.pubsub.Messenger
import com.minisdk.pubsub.data.Message

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.name

    private val messenger = Messenger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "[pubsubtest] Mainactivity start")
        messenger.subscribe("native"){message ->
            Log.d(TAG, "[pubsubtest] on native: " + message.data)
        }
        messenger.publish(Message("test", "hello form app"))
    }
}