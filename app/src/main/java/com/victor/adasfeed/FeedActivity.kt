package com.victor.adasfeed

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.adasfeed.FakePosts.makePostList
import com.victor.adasfeed.passandodados.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val EXTRA_KEY = "EXTRA_KEY"

class FeedActivity : AppCompatActivity() {
    private lateinit var buttonNewPost: Button
    private lateinit var buttonRenderNewList: Button
    private lateinit var buttonGoToProfile: Button
    private lateinit var rvStories: RecyclerView
    private lateinit var rvPosts: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var storiesAdapter: StoriesAdapter
    private lateinit var headerView: View
    private lateinit var textName: TextView
    private lateinit var textNickname: TextView
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ciclo de vida", "onCreate")
        Log.d("contexto no onCreate", applicationContext.toString())
        setContentView(R.layout.activity_feed)

        textName = findViewById(R.id.textName)
        textNickname = findViewById(R.id.textNickname)

        var oldUser = User(
            userName = "Andrey Freitas",
            userNickname = "@andreyfreitas",
            imageUser = R.drawable.user1,
            tel = "+55 (11) 123456789"
        )

        val extras = intent.extras
        var uri = Uri.parse("")

        // envia por intents - putExtra
        // recebe por Bundle
        extras?.let { bundle ->
            user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(EXTRA_KEY, User::class.java)
            } else {
                bundle.getParcelable(EXTRA_KEY) as? User
            }
            user?.let { safeUser ->
                textName.text = getString(R.string.profile_name, safeUser.userName)
                textNickname.text = safeUser.userNickname
            }
        }

        if (user == null) {
            user = oldUser
        }

        val intent = Intent(applicationContext, ProfileActivity::class.java).apply {
            putExtra(EXTRA_KEY, user)
        }

        // inicializa views (findViewById())
        initViews()
        textName.text = user!!.userName
        textNickname.text = user!!.userNickname
        // monta os recyclerViews
        setupRecyclerViews()
        // criar os listeners
        setupClickListeners()
        setupNavigationListeners(intent)

        CoroutineScope(Dispatchers.Main).launch {
            renderButtonText()
        }

        // melhores praticas: é mandar pra ViewModel
        lifecycleScope.launch {
            renderButtonText()
        }

        lifecycleScope.launch {
            renderEditText()
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
//        buttonRenderNewList = findViewById(R.id.buttonRenderNewList)
        buttonGoToProfile = findViewById(R.id.buttonGoToProfile)
        headerView = findViewById(R.id.headerView)
    }

    private fun setupRecyclerViews() {
        // layoutmanager no código prioriza sobre o que está no XML
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
//        buttonRenderNewList.setOnClickListener {
//            val newPostList = makePostList()
//            postAdapter.setNewList(newPostList)
//        }
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

        headerView.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
    }

    private fun setupNavigationListeners(intent: Intent) {
        buttonGoToProfile.setOnClickListener {
            startActivity(intent)
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//    }
//    override fun onRestoreInstanceState(
//        savedInstanceState: Bundle?,
//        persistentState: PersistableBundle?
//    ) {
//        savedInstanceState?.getString("key")
//        super.onRestoreInstanceState(savedInstanceState, persistentState)
//    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo de vida FA", "onStart")
        Log.d("contexto no onStart", applicationContext.toString())
//        rvPosts.setOnClickListener { print("cliquei") }
//        Toast.makeText(applicationContext, "Estou no onStart", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo de vida FA", "onResume")
        Log.d("contexto no onResume", applicationContext.toString())
//        rvPosts.setOnClickListener { print("cliquei") }
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo de vida FA", "onPause")
        Log.d("contexto no onPause", applicationContext.toString())
        buttonNewPost.text = "onPause"
//        Toast.makeText(applicationContext, "salvando status da lista", Toast.LENGTH_LONG).show()
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

//        val toolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
//
//        toolbar.title = "Ada's News"

//        var userName = "Loading..."
//        val textName = findViewById<TextView>(R.id.textName)

//        CoroutineScope(Dispatchers.Main).launch {
//            // simula uma requisicao do servidor
//            delay(5000L)
//            val userData = "Victor"
//            userName = if (userData.isBlank()) {
//                "Desconhecido"
//            } else {
//                userData
//            }
//        }.invokeOnCompletion { textName.text = userName }