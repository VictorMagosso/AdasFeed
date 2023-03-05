package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.victor.adasfeed.passandodados.User

class ProfileActivity : AppCompatActivity() {
    // quebrar o codigo de proposito
    // private val context = applicationContext.toString()

    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var buttonCall: Button
    private lateinit var imageUser: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        imageUser = findViewById(R.id.imageUser)

        val extras = intent.extras
        var uri = Uri.parse("")

        // envia por intents - putExtra
        // recebe por Bundle
        extras?.let { bundle ->
            val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(EXTRA_KEY, User::class.java)
            } else {
                bundle.getParcelable(EXTRA_KEY) as? User
            }

            user?.let { safeUser ->
                textUserName.text = safeUser.userName
                textNickname.text = safeUser.userNickname
                imageUser.setImageResource(safeUser.imageUser)
                uri = safeUser.tel?.let {
                    Uri.parse("tel:${safeUser.tel}")
                } ?: Uri.parse("tel:")
            }
        }

        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }
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