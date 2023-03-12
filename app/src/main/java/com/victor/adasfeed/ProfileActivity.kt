package com.victor.adasfeed

import android.app.Activity
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
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.victor.adasfeed.databinding.ActivityMainBinding
import com.victor.adasfeed.databinding.ActivityProfileBinding
import com.victor.adasfeed.passandodados.User

class ProfileActivity : AppCompatActivity() {
    // quebrar o codigo de proposito
    // private val context = applicationContext.toString()
    private lateinit var binding: ActivityProfileBinding
    private var name: String = ""
    private var nickname: String = ""
    private lateinit var userIntent: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d("contexto PA", applicationContext.toString())
        Log.d("ciclo de vida PA", "onCreate")

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
                userIntent = safeUser
                name = safeUser.userName
                nickname = safeUser.userNickname
                with(binding) {
                    textUserName.text = safeUser.userName
                    textNickname.text = safeUser.userNickname
                    imageUser.setImageResource(safeUser.imageUser)
                }
                uri = safeUser.tel?.let {
                    Uri.parse("tel:${safeUser.tel}")
                } ?: Uri.parse("tel:")
            }
        }

        with(binding) {
            buttonContact.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, uri)
                startActivity(intent)
                finish()
            }
            editUserName.doAfterTextChanged {
                name = it.toString()
            }
            editNickname.doAfterTextChanged {
                nickname = it.toString()
            }
            saveButton.setOnClickListener {
                textUserName.text = name
                textNickname.text = nickname
            }
            buttonReturnToFeed.setOnClickListener {
                val intent = Intent().apply {
                    putExtra(EXTRA_KEY, userIntent.copy(userName = name, userNickname = nickname))
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
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