package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.victor.adasfeed.passandodados.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var editUserName: EditText
    private lateinit var editNickname: EditText
    private lateinit var buttonCall: Button
    private lateinit var buttonReturn: Button
    private lateinit var imageUser: ImageView
    private lateinit var fabSave: View
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        editUserName = findViewById(R.id.editUserName)
        editNickname = findViewById(R.id.editNickname)
        buttonCall = findViewById(R.id.buttonContact)
        buttonReturn = findViewById(R.id.buttonReturnToFeed)
        imageUser = findViewById(R.id.imageUser)

        val extras = intent.extras
        var uri = Uri.parse("")

        extras?.let { bundle ->
            user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(EXTRA_KEY, User::class.java)
            } else {
                bundle.getParcelable(EXTRA_KEY) as? User
            }

            user?.let { safeUser ->
                textUserName.text = getString(R.string.profile_name, safeUser.userName)
                textNickname.text = safeUser.userNickname
                imageUser.setImageResource(safeUser.imageUser)
                uri = safeUser.tel?.let {
                    Uri.parse("tel:${safeUser.tel}")
                } ?: Uri.parse("tel:")
            }
        }

        fabSave = findViewById(R.id.fabSave)
        fabSave.setOnClickListener { view ->
            if (editUserName.text.isNotBlank() && editNickname.text.isNotBlank()) {
                textUserName.text = getString(R.string.profile_name, editUserName.text)
                textNickname.text = editNickname.text
                user = user?.copy(
                    userName = editUserName.text.toString(),
                    userNickname = editNickname.text.toString()
                )
                Snackbar.make(view, "Dados salvos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()

            }
        }

        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
            finish()
        }

        buttonReturn.setOnClickListener {
            val intent = Intent(applicationContext, FeedActivity::class.java).apply {
                putExtra(EXTRA_KEY, user)
            }
            startActivity(intent)
        }

        editUserName.setOnKeyListener { view, i, keyEvent ->
            textUserName.text = getString(R.string.profile_name, editUserName.text)
            false
        }

        editNickname.setOnKeyListener { view, i, keyEvent ->
            textNickname.text = editNickname.text
            false
        }

    }
}