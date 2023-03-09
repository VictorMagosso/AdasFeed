package com.victor.adasfeed

data class Post(
    val userName: String,
    val description: String,
    val imagePost: Int,
    val imageUser: Int,
    val isLiked: Boolean = false,
    val isFavorite: Boolean = false,
)