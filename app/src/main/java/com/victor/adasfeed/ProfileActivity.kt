package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.victor.adasfeed.passandodados.User

class ProfileActivity : AppCompatActivity() {
    // quebrar o codigo de proposito
    // private val context = applicationContext.toString()

    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var buttonCall: Button
    private lateinit var imageUser: ImageView
    private lateinit var userNameField: EditText
    private lateinit var nickNameField: EditText
    private lateinit var buttonSave: FloatingActionButton
    private lateinit var User: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        imageUser = findViewById(R.id.imageUser)
        userNameField = findViewById<EditText>(R.id.editUserName)
        nickNameField = findViewById<EditText>(R.id.editNickname)
        buttonSave = findViewById(R.id.buttonSaveProfile)

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
            // mais de um parametro na string
//            textUserName.text = getString(R.string.profile_name, safeUser.userName, safeUser.userNickname)

            user?.let { safeUser ->
                textUserName.text = getString(R.string.profile_name, safeUser.userName)
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
            finish()
        }

        fun updateUserName(newName: String) {
            textUserName.text = newName
        }

        fun updateNickame(newNickname: String) {
            textNickname.text = newNickname
        }

        userNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changed
                val newName = s.toString()

                // Update the name on the view in real-time
                updateUserName(newName)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text is changed
            }
        })

        nickNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changed
                val newName = "@"+s.toString()

                // Update the name on the view in real-time
                updateNickame(newName)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text is changed
            }
        })

        buttonSave.setOnClickListener {
            val userName = userNameField.text.toString()
            val nickName = nickNameField.text.toString()

            //  updateUserProfile(newUserName,newUserNickname)
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

