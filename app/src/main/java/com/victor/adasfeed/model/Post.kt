package com.victor.adasfeed.model

import java.util.UUID

data class Post(
    val uid: UUID = UUID.randomUUID(),
    val userName: String,
    val description: String,
    val imagePost: Int,
    val imageUser: Int,
    val isLiked: Boolean = false,
    val isFavorite: Boolean = false,
)
