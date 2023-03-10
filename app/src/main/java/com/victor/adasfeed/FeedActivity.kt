package com.victor.adasfeed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.adasfeed.passandodados.User
import com.victor.adasfeed.passandodados.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val EXTRA_KEY = "EXTRA_KEY"

class FeedActivity : AppCompatActivity() {
    private lateinit var buttonNewPost: Button
    private lateinit var buttonGoToProfile: Button
    private lateinit var rvStories: RecyclerView
    private lateinit var rvPosts: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var headerView: View
    private lateinit var tvUsername: TextView
    private lateinit var tvNickname: TextView
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        Log.d("ciclo de vida", "onCreate")
        Log.d("contexto no onCreate", applicationContext.toString())
        initViews()
        setupData()
        setupRecyclerViews()
        setupClickListeners()
        setupNavigationListeners()

        CoroutineScope(Dispatchers.Main).launch {
            renderButtonText()
        }

        lifecycleScope.launch {
            renderButtonText()
        }

        lifecycleScope.launch {
            renderEditText()
        }
    }

    private fun setupData() {
        val extras = intent.extras
        extras?.let { bundle ->
            user = load(EXTRA_KEY, bundle)
            user?.let { safetyUser ->
                tvUsername.text = safetyUser.userName ?: "Nao tem nome"
                tvNickname.text = safetyUser.userNickname ?: " ---- "
            }
        }

        if (user == null) {
            user = User(
                userName = "Andrey Freitas",
                userNickname = "@andreyfreitas",
                imageUser = R.drawable.user1,
                tel = "+55 (11) 123456789"
            )
            tvUsername.text = user?.userName
            tvNickname.text = user?.userNickname
        }
    }

    private suspend fun renderButtonText() {
        delay(1L)
        Log.d("acabou o delay", " - ")
    }

    private suspend fun renderEditText() {
        delay(1L)
        Log.d("acabou o delay", " - ")
    }

    private fun initViews() {
        rvStories = findViewById(R.id.rvStories)
        rvPosts = findViewById(R.id.rvPosts)
        buttonNewPost = findViewById(R.id.buttonNewPost)
        buttonGoToProfile = findViewById(R.id.buttonGoToProfile)
        headerView = findViewById(R.id.headerView)
        tvUsername = findViewById(R.id.textName)
        tvNickname = findViewById(R.id.textNickname)
    }

    private fun setupRecyclerViews() {
        val storiesLayoutManager = LinearLayoutManager(applicationContext)
        storiesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        storiesAdapter = StoriesAdapter()
        postAdapter = PostAdapter()

        rvStories.apply {
            adapter = storiesAdapter
            layoutManager = storiesLayoutManager
        }

        rvPosts.apply {
            adapter = postAdapter
        }
    }

    private fun setupClickListeners() {
        buttonNewPost.setOnClickListener {
            postAdapter.addNewPost(
                Post(
                    userName = "Esse é novo",
                    description = "Estou sendo adicionado do feed activity 2",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories2
                )
            )
        }
    }

    private fun setupNavigationListeners() {
        val intent = Intent(applicationContext, ProfileActivity::class.java).apply {
            putExtra(EXTRA_KEY, user)
        }

        buttonGoToProfile.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo de vida FA", "onStart")
        Log.d("contexto no onStart", applicationContext.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo de vida FA", "onResume")
        Log.d("contexto no onResume", applicationContext.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo de vida FA", "onPause")
        Log.d("contexto no onPause", applicationContext.toString())
        buttonNewPost.text = "onPause"
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo de vida FA", "onStop")
        Log.d("contexto no onStop", applicationContext.toString())
        buttonNewPost.text = "onStop"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo de vida", "onDestroy")
        Log.d("contexto no onDestroy", applicationContext.toString())
    }
}