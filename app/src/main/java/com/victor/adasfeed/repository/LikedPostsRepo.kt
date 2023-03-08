package com.victor.adasfeed.repository

import com.victor.adasfeed.model.Post

object LikedPostsRepo {
    private val storage = mutableListOf<Post>()

    fun getAll() = storage.toList()

    fun add(p: Post) {
        storage.add(p)
    }

    fun remove(p: Post) {
        storage.remove(p)
    }

    override fun toString(): String {
        return storage.toString()
    }
}
