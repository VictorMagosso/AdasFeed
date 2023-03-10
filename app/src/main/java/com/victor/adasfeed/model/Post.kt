package com.victor.adasfeed.model

import java.util.UUID

data class Post(
    val uid: UUID = UUID.randomUUID(),
    val userName: String,
    val description: String,
    val imagePost: Int,
    val imageUser: Int,
    var isLiked: Boolean = false,
    var isFavorite: Boolean = false,
) {
    override fun toString() = uid.toString()
}
