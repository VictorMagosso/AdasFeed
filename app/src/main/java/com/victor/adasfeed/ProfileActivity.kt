package com.victor.adasfeed

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.victor.adasfeed.passandodados.User

class ProfileActivity : AppCompatActivity() {
    // quebrar o codigo de proposito
    // private val context = applicationContext.toString()

    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var buttonCall: Button
    private lateinit var btnReturnToFeed: Button
    private var userProfile: User? = null
    private lateinit var imageUser: ImageView
    private lateinit var etUsername: EditText
    private lateinit var etNickname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        btnReturnToFeed = findViewById(R.id.buttonReturnToFeed)
        imageUser = findViewById(R.id.imageUser)
        etUsername = findViewById(R.id.editUserName)
        etNickname = findViewById(R.id.editNickaname)

        val extras = intent.extras
        var uri = Uri.parse("")

        // envia por intents - putExtra
        // recebe por Bundle
        uri = setupDataUser(extras, uri)
        setListeners(uri)
    }

    private fun setupDataUser(extras: Bundle?, uri: Uri?): Uri? {
        var uri1 = uri
        extras?.let { bundle ->
            val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(EXTRA_KEY, User::class.java)
            } else {
                bundle.getParcelable(EXTRA_KEY) as? User
            }

            user?.let { safeUser ->
                val userName = safeUser.userName
                val userNickname = safeUser.userNickname
                val imageUser1 = safeUser.imageUser
                val tel = safeUser.tel
                userProfile = User(userName, userNickname,imageUser1,tel)
                textUserName.text = userName
                textNickname.text = userNickname
                imageUser.setImageResource(imageUser1)
                etUsername.setText(userName)
                etNickname.setText(userNickname)

                uri1 = tel?.let {
                    Uri.parse("tel:$tel")
                } ?: Uri.parse("tel:")
            }
        }
        return uri1
    }

    private fun setListeners(uri: Uri?) {
        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        btnReturnToFeed.setOnClickListener {
            val result = Intent().putExtra("Updated user", userProfile)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        etUsername.addTextChangedListener { editable ->
            textUserName.text = editable.toString()
        }
        etNickname.addTextChangedListener { editable ->
            textNickname.text =
                if (editable.toString().isNotBlank()) {
                    "@${editable.toString()}"
                } else {
                    "----"
                }
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