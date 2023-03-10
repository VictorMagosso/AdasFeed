package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.victor.adasfeed.passandodados.User
import com.victor.adasfeed.utils.ExtraKeys
import com.victor.adasfeed.utils.ExtraKeys.EXTRA_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    // quebrar o codigo de proposito
    // private val context = applicationContext.toString()

    private lateinit var textUserName: TextView
    private lateinit var textNickname: TextView
    private lateinit var buttonCall: Button
    private lateinit var buttonGoToFeed: Button
    private lateinit var buttonSaveInfo: FloatingActionButton
    private lateinit var imageUser: ImageView
    private lateinit var editUserName: EditText
    private lateinit var editNickname: EditText
    private lateinit var loadingContainer: View
    private lateinit var editTextContainer: LinearLayout

    private var uri = Uri.parse("")
    private var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

        initViews()

        // envia por intents - putExtra
        // recebe por Bundle
        val extras = intent.extras
        user = getUserFromExtras(extras)

        initButtonSaveInfoListener()
        initTexts(user)
        initEditTextListeners()
        initButtonSaveInfoListener()
        initEditTexts(user)
        initButtonCallListener()
        initButtonGoToFeedListener()
    }

    private fun initButtonGoToFeedListener() {
        buttonGoToFeed.setOnClickListener {
            val intent =
                Intent(applicationContext, FeedActivity::class.java).putExtra(EXTRA_KEY, user)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        textUserName = findViewById(R.id.textUserName)
        textNickname = findViewById(R.id.textNickname)
        buttonCall = findViewById(R.id.buttonContact)
        buttonGoToFeed = findViewById(R.id.buttonReturnToFeed)
        buttonSaveInfo = findViewById(R.id.buttonSaveInfo)
        imageUser = findViewById(R.id.imageUser)
        editUserName = findViewById(R.id.editUserName)
        editNickname = findViewById(R.id.editNickaname)
        loadingContainer = findViewById(R.id.loadingContainer)
        editTextContainer = findViewById(R.id.editTextContainer)
    }

    private fun initButtonCallListener() {
        buttonCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
            finish()
        }
    }

    private fun initButtonSaveInfoListener() {
        buttonSaveInfo.setOnClickListener {
            hideComponents()

            lifecycleScope.launch {
                handleSaveUserClick()
            }.invokeOnCompletion {
                showComponents()
            }
        }
    }

    private fun showComponents() {
        editTextContainer.visibility = View.VISIBLE
        loadingContainer.visibility = View.GONE
        buttonSaveInfo.isEnabled = true
        buttonGoToFeed.isEnabled = true
        buttonCall.isEnabled = true
    }

    private fun hideComponents() {
        editTextContainer.visibility = View.GONE
        loadingContainer.visibility = View.VISIBLE
        buttonSaveInfo.isEnabled = false
        buttonGoToFeed.isEnabled = false
        buttonCall.isEnabled = false
    }

    private fun String?.or(defaultValue: String?) = if (this.isNullOrBlank()) defaultValue else this

    private suspend fun handleSaveUserClick() {
        delay(5000L)
        val newUserName = editUserName.text.toString().or(user?.userName)
        val newUserNickname = editNickname.text.toString().or(user?.userNickname)

        user = user?.copy(
            userName = newUserName.orEmpty(),
            userNickname = newUserNickname.orEmpty()
        )
        Toast.makeText(applicationContext, user?.userName.orEmpty(), Toast.LENGTH_LONG).show()
    }

    private fun initTexts(
        user: User?,
    ) {
        // mais de um parametro na string
        // textUserName.text = getString(R.string.profile_name, safeUser.userName, safeUser.userNickname)

        user?.let { safeUser ->
            textUserName.text = getString(R.string.profile_name, safeUser.userName)
            textNickname.text = safeUser.userNickname
            imageUser.setImageResource(safeUser.imageUser)
            uri = safeUser.tel?.let {
                Uri.parse("tel:${safeUser.tel}")
            } ?: Uri.parse("tel:")
        }
    }

    private fun getUserFromExtras(extras: Bundle?) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            extras?.getParcelable(EXTRA_KEY, User::class.java)
        } else {
            extras?.getParcelable(EXTRA_KEY) as? User
        }

    private fun initEditTexts(user: User?) {
        user?.let {
            with(it) {
                editUserName.hint = userName
                editNickname.hint = userNickname
            }
        }
    }

    private fun initEditTextListeners() {
        editUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textUserName.text = p0
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }

        })

        editNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textNickname.text = p0
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }

        })
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
