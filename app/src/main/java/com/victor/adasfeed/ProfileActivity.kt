package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.victor.adasfeed.passandodados.User
import com.victor.adasfeed.passandodados.load

class ProfileActivity : AppCompatActivity() {
    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var buttonCall: Button
    private lateinit var imageUser: ImageView
    private lateinit var fabSave: FloatingActionButton
    private lateinit var backToHome: Button
    private lateinit var userNameField: EditText
    private lateinit var nickNameField: EditText
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var uri = Uri.parse("")
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

        initViews()
        setupButtons(uri)

        val extras = intent.extras
        extras?.let { bundle ->
            user = load(EXTRA_KEY, bundle)
            user?.let { safeUser ->
                textUserName.text = getString(R.string.profile_name, safeUser.userName)
                textNickname.text = safeUser.userNickname
                imageUser.setImageResource(safeUser.imageUser)
                uri = safeUser.tel?.let {
                    Uri.parse("tel:${safeUser.tel}")
                } ?: Uri.parse("tel:")
            }
        }
    }

    private fun initViews() {
        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        imageUser = findViewById(R.id.imageUser)
        fabSave = findViewById(R.id.fabSave)
        backToHome = findViewById(R.id.buttonReturnToFeed)
        userNameField = findViewById(R.id.editUserName)
        nickNameField = findViewById(R.id.editNickaname)
    }

    private fun setupButtons(uri: Uri) {
        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
            finish()
        }

        fabSave.setOnClickListener {
            val name = userNameField.text.toString()
            val nickName = userNameField.text.toString()
            textUserName.text = name
            textNickname.text = nickName
            user?.updateUser(name, nickName)
        }

        backToHome.setOnClickListener {
            val intent = Intent(applicationContext, FeedActivity::class.java).apply {
                putExtra(EXTRA_KEY, user)
            }
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

    override fun onDestroy() {
        val intent = Intent(applicationContext, FeedActivity::class.java).apply {
            putExtra(EXTRA_KEY, user)
        }
        startActivity(intent)
        super.onDestroy()
        Log.d("ciclo de vida PA", "onDestroy")
    }
}