package com.victor.adasfeed.ui.activity.resultcontract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.victor.adasfeed.getUserFromIntent
import com.victor.adasfeed.model.User
import com.victor.adasfeed.ui.activity.ProfileActivity

class UserActivityResultContract : ActivityResultContract<User, User?>() {

    override fun createIntent(context: Context, input: User): Intent {
        return Intent(context, ProfileActivity::class.java).apply {
            putExtra(USER_EXTRA_KEY, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): User? {
        return when (resultCode) {
            Activity.RESULT_OK -> getUserFromIntent(intent)
            else -> null
        }
    }

    companion object {
        const val USER_EXTRA_KEY = "INTENT_EXTRA_USER_KEY"
    }
}