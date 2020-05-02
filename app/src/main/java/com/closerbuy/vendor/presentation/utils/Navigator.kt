package com.closerbuy.vendor.presentation.utils

import android.content.Context
import com.closerbuy.vendor.presentation.view.login.LoginActivity

/**
 * Created by Vijay on 27/3/20.
 */

object Navigator {

    fun navigateToLogin(context: Context) {
        context.startActivity(LoginActivity.getCallingIntent(context))
    }

}