package com.victor.adasfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        var userName = "Loading..."
        val textName = findViewById<TextView>(R.id.textName)

        CoroutineScope(Dispatchers.Main).launch {
            // simula uma requisicao do servidor
            delay(5000L)
            val userData = "Victor"
            userName = if (userData.isBlank()) {
                "Desconhecido"
            } else {
                userData
            }
        }.invokeOnCompletion { textName.text = userName }
    }
}