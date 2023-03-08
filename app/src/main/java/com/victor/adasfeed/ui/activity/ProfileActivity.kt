package com.victor.adasfeed.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.victor.adasfeed.R
import com.victor.adasfeed.getUserFromIntent
import com.victor.adasfeed.model.User
import com.victor.adasfeed.ui.activity.resultcontract.UserActivityResultContract

class ProfileActivity : AppCompatActivity() {
    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var buttonCall: Button
    private lateinit var imageUser: ImageView
    private lateinit var backToFeedBtn: AppCompatButton
    private lateinit var etName: EditText
    private lateinit var etNickname: EditText
    private lateinit var fabSave: FloatingActionButton

    private lateinit var dialUri: Uri
    private lateinit var user: User
    private var userWasUpdated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
        handleIntentExtras()
        handleSetClickListeners()
        handleAddTextChangedListener(etName, textUserName, R.string.profile_name)
        handleAddTextChangedListener(etNickname, textNickname, R.string.profile_nickname)
    }

    private fun initViews() {
        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        imageUser = findViewById(R.id.imageUser)
        backToFeedBtn = findViewById(R.id.buttonReturnToFeed)
        etName = findViewById(R.id.editUserName)
        etNickname = findViewById(R.id.editNickaname)
        fabSave = findViewById(R.id.fabSave)
    }

    private fun handleSetClickListeners() {
        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, dialUri)
            startActivity(intent)
        }

        backToFeedBtn.setOnClickListener {
            setActivityResult()
            finish()
        }

        fabSave.setOnClickListener {
            val res = updateUser()

            Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUser() : String {
        val etUsernameInput = etName.text.toString()
        val etNicknameInput = "@${etNickname.text}"

        val username = if (etUsernameInput.length >= 3) etUsernameInput else user.userName
        val nickname = if (etNicknameInput.length >= 4) etNicknameInput else user.userNickname

        return if (username != user.userName && nickname != user.userNickname) {
            user = User(
                username,
                nickname,
                user.imageUser,
                user.tel
            )

            userWasUpdated = true

            "Usuário foi atualizado."
        } else "Campo deve ter mais que 3 caracteres e ser um novo valor."

    }

    override fun onDestroy() {
        super.onDestroy()
        setActivityResult()
    }

    private fun setActivityResult() {
        if (userWasUpdated) {
            val resultIntent = Intent().apply { putExtra(UserActivityResultContract.USER_EXTRA_KEY, user) }
            setResult(Activity.RESULT_OK, resultIntent)
        } else
            setResult(Activity.RESULT_CANCELED)
    }

    private fun handleIntentExtras() {
        val extras = intent.extras
        dialUri = Uri.parse("")

        extras?.let {
            val bundleUser = getUserFromIntent(intent)

            bundleUser?.let { safeUser ->
                textUserName.text = getString(R.string.profile_name, safeUser.userName)
                textNickname.text = safeUser.userNickname
                imageUser.setImageResource(safeUser.imageUser)

                dialUri = safeUser.tel?.let {
                    Uri.parse("tel:${safeUser.tel}")
                } ?: Uri.parse("tel:")

                user = bundleUser
            }
        }
    }

    private fun handleAddTextChangedListener(editText: EditText, target: TextView, stringRes: Int) {
        editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                target.text = getString(stringRes, s.toString())
            }

        })
    }
}