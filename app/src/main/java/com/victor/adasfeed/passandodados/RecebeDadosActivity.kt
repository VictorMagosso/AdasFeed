package com.victor.adasfeed.passandodados

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.victor.adasfeed.R

class RecebeDadosActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recebe_dados)

        val textUserName = findViewById<TextView>(R.id.textUserName)
        val textUserNickname = findViewById<TextView>(R.id.textNickname)
        val imageUser = findViewById<ImageView>(R.id.imageUser)

        val extras = intent.extras

        extras?.let { bundle ->
            //texto recebido
            val user = bundle.getParcelable("CHAVE_PARA_ACESSAR_INFO", User::class.java)

            textUserName.text = user?.userName ?: "Nao tem nome"
            textUserNickname.text = user?.userNickname ?: " ---- "
            imageUser.setImageResource(user?.imageUser ?: R.drawable.placeholder)
        }
    }
}