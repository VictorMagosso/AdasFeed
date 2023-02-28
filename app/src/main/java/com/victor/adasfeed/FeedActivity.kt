package com.victor.adasfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val rvStories = findViewById<RecyclerView>(R.id.rvStories)
        val rvPosts = findViewById<RecyclerView>(R.id.rvPosts)
        val buttonNewPost = findViewById<Button>(R.id.buttonNewPost)
        val buttonRenderNewList = findViewById<Button>(R.id.buttonRenderNewList)

        // precede o que está no XML
        val storiesLayoutManager = LinearLayoutManager(applicationContext)
        storiesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val postAdapter = PostAdapter()
        rvStories.apply {
            adapter = StoriesAdapter()
            layoutManager = storiesLayoutManager
        }

        rvPosts.apply {
            adapter = postAdapter
        }

        var newPost = Post(
            userName = "Feed teste",
            description = "Estou sendo adicionado do feed activity",
            imageUser = R.drawable.user3,
            imagePost = R.drawable.stories1
        )
        buttonNewPost.setOnClickListener {
            postAdapter.addNewPost(
                newPost
            )
            newPost = Post(
                userName = "Esse é novo",
                description = "Estou sendo adicionado do feed activity 2",
                imageUser = R.drawable.user4,
                imagePost = R.drawable.stories2
            )
        }

        buttonRenderNewList.setOnClickListener {
            val newPostList = listOf(
                Post(
                    userName = "Esse é novo",
                    description = "Estou sendo adicionado do feed activity 2",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories2
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
                Post(
                    userName = "O DiffUtilCallback",
                    description = "Diff util callback",
                    imageUser = R.drawable.user4,
                    imagePost = R.drawable.stories4
                ),
            )
            postAdapter.setNewList(newPostList)
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
    }
}