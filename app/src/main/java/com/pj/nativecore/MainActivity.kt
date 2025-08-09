package com.pj.nativecore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minisdk.pubsub.Messenger

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.name

    private val messenger = Messenger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}