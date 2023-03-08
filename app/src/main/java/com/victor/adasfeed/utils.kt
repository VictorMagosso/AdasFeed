package com.victor.adasfeed

import android.content.Intent
import android.os.Build
import com.victor.adasfeed.model.User
import com.victor.adasfeed.ui.activity.resultcontract.UserActivityResultContract

fun getUserFromIntent(intent: Intent?) : User? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        intent?.extras?.getParcelable(UserActivityResultContract.USER_EXTRA_KEY, User::class.java)
    else
        intent?.extras?.getParcelable(UserActivityResultContract.USER_EXTRA_KEY) as? User
}