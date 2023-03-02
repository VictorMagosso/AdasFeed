package com.victor.adasfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ProfileActivity : AppCompatActivity() {
    // quebrar o codigo de proposito
//    private val context = applicationContext.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo de vida PA", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo de vida PA", "onResume")
    }
}