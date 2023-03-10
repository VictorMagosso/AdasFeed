package com.victor.adasfeed.passandodados

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private fun String?.orEmptyString() = this ?: ""

@Parcelize
data class User(
    var userName: String,
    var userNickname: String,
    val imageUser: Int,
    val tel: String? = null,
) : Parcelable {
    fun updateUser(newName: String, newNickname: String): Boolean {
        if (newName.isNotEmpty()) {
            userName = newName
        }
        if (newNickname.isNotEmpty()) {
            userNickname = "@$newNickname"
        }
        return !(newName.isNullOrBlank() and newNickname.isEmpty())
    }
}

fun load(EXTRA_KEY: String, bundle: Bundle): User? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        bundle.getParcelable(EXTRA_KEY, User::class.java)
    else
        bundle.getParcelable(EXTRA_KEY) as? User