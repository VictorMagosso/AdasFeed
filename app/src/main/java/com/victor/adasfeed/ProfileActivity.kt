package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.victor.adasfeed.passandodados.User
import com.victor.adasfeed.passandodados.extractUser

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
    private lateinit var buttonReturnToFeed: Button
    private var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

        initViews()
        // criar os listeners
        val extras = intent.extras
        var uri = Uri.parse("")
        extras?.let { bundle ->
            user = extractUser(EXTRA_KEY, bundle)

            user?.let { safeUser ->
                textUserName.text = getString(R.string.profile_name, safeUser.userName)
                textNickname.text = safeUser.userNickname
                imageUser.setImageResource(safeUser.imageUser)

                uri = safeUser.tel?.let {
                    Uri.parse("tel:${safeUser.tel}")
                } ?: Uri.parse("tel:")
            }
        }
        setupClickListeners(uri)
    }

    fun updateUserName(newName: String) {
        textUserName.text = newName
    }

    fun updateNickname(newNickname: String) {
        textNickname.text = newNickname
    }

    private fun setupClickListeners(uri: Uri) {

        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
            finish()
        }

        userNameField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changed
                val newName = "Nome: " + s.toString()

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
                val newNickname = "@" + s.toString()

                // Update the name on the view in real-time
                updateNickname(newNickname)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text is changed
            }
        })

        buttonReturnToFeed.setOnClickListener {
            val intent = Intent(applicationContext, FeedActivity::class.java).apply {
                putExtra(EXTRA_KEY, user)
            }
            startActivity(intent)
        }

        buttonSave.setOnClickListener {
            val newUserName = userNameField.text.toString()
            val newUserNickname = nickNameField.text.toString()

            if (user?.updateUserProfile(newUserName, newUserNickname) == true) {
                Toast.makeText(this, "Update with sucess", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error updating", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initViews() {
        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        imageUser = findViewById(R.id.imageUser)
        userNameField = findViewById(R.id.editUserName)
        nickNameField = findViewById(R.id.editNickname)
        buttonSave = findViewById(R.id.buttonSaveProfile)
        buttonReturnToFeed = findViewById(R.id.buttonReturnToFeed)
    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo de vida PA", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo de vida PA", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo de vida PA", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo de vida PA", "onStop")
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

