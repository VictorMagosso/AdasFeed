package com.victor.adasfeed.passandodados

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.victor.adasfeed.R

class EnviaDadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envia_dados)

        // recebi ele da api
        val user = User("Victor", "@victormagosso", R.drawable.user5)

        val textDataToSend = findViewById<TextView>(R.id.textDataToSend)
        val buttonGoToActivity = findViewById<Button>(R.id.buttonSendData)
        val intent = Intent(applicationContext, RecebeDadosActivity::class.java).apply {
            putExtra("CHAVE_PARA_ACESSAR_INFO", user)
        }
//        buttonGoToActivity.setOnClickListener {
//            startActivity(intent)
//        }
//        val callIntent = Intent(
//            Intent.ACTION_DIAL,
//            uri,
//        ).apply {
//            putExtra(
//                Intent.EXTRA_PHONE_NUMBER, "tel:5511999999999"
//            )
//        }

        val uri = Uri.parse("tel:5511999999999")
        val callIntent = Intent(
            Intent.ACTION_DIAL,
            uri,
        )

        val mapIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:17.7749,-100.4194")
        )

        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.android.com")
        )

        val openAppIntent = Intent(
            Intent.ACTION_VIEW,
        ).putExtra(Intent.EXTRA_PACKAGE_NAME, "br.com.brainweb.ifood")

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("johndoe@email.com")) // para quem envia
            putExtra(Intent.EXTRA_SUBJECT, "Assunto do e-mail")
            putExtra(Intent.EXTRA_TEXT, "Eu sou uma mensagem do ${user.userName}")
        }

        buttonGoToActivity.setOnClickListener {
            startActivity(emailIntent)
        }
    }
}

