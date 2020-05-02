package com.closerbuy.vendor

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by Vijay on 10/3/20.
 */

class UserApplication : Application() {
    companion object {

        var mAppContext: UserApplication? = null

        fun getAppInstance(): UserApplication {
            return mAppContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = this
        Stetho.initializeWithDefaults(this)

    }

}