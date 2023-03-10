package com.victor.adasfeed.mock

import com.victor.adasfeed.R
import com.victor.adasfeed.model.Post
import com.victor.adasfeed.model.Story

object Faker {
    fun makePostList() = mutableListOf(
        Post(
            userName = "Jonas do Dark",
            description = "Hoje foi dia de muita gravação !!!",
            imagePost = R.drawable.feed1,
            imageUser = R.drawable.user1,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ), Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
        Post(
            userName = "Marcela",
            description = "Olhem meus doguinhos que fofos",
            imagePost = R.drawable.feed2,
            imageUser = R.drawable.user2,
        ),
    )

    fun makeStoryList() = listOf(
        Story(
            "Joao",
            "15 m",
            imageUser = R.drawable.user1,
            imagePost = R.drawable.stories1,
        ),
        Story(
            "Victor",
            "28 m",
            imageUser = R.drawable.user2,
            imagePost = R.drawable.stories2,
        ), Story(
            "Marina da Silva Costa",
            "59 m",
            imageUser = R.drawable.user3,
            imagePost = R.drawable.stories3,
        ), Story(
            "Ninja desconhecido",
            "60 m",
            imageUser = R.drawable.user4,
            imagePost = R.drawable.stories4,
        )
    )
}