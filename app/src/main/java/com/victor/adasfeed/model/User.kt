package com.victor.adasfeed.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val userName: String,
    val userNickname: String,
    val imageUser: Int,
    val tel: String? = null,
) : Parcelable
