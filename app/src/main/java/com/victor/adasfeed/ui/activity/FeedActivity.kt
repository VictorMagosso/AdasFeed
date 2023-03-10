package com.victor.adasfeed.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.victor.adasfeed.ui.recyclerview.adapter.PostAdapter
import com.victor.adasfeed.R
import com.victor.adasfeed.model.Post
import com.victor.adasfeed.ui.recyclerview.adapter.StoriesAdapter
import com.victor.adasfeed.model.User

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

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        user = User(
            userName = "Andrey Freitas",
            userNickname = "@andreyfreitas",
            imageUser = R.drawable.user1,
            tel = "+55 (11) 123456789"
        )

        val intent = Intent(applicationContext, ProfileActivity::class.java).apply {
            putExtra(EXTRA_KEY, user)
        }

        initViews()
        setHeaderInfo()
        setupRecyclerViews()
        setupClickListeners()
        setupNavigationListeners(intent)
    }

    private fun setHeaderInfo() {
        tvUsername.text = user.userName
        tvNickname.text = user.userNickname
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
            postAdapter.addPost(
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
}
