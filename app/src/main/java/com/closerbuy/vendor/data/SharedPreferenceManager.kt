package com.closerbuy.vendor.data

import android.content.Context
import android.content.SharedPreferences
import com.closerbuy.vendor.R
import com.closerbuy.vendor.UserApplication


/**
 * Created by Vijay on 3/12/19.
 */

class SharedPreferenceManager {

    companion object {
        var KEY_VENDOR_ID = "vendorId"
        var KEY_VENDOR_AUTH_TOKEN = "authToken"
        var KEY_IS_USER_REGISTERED = "isUserRegistered"
        var KEY_ORDER_ID = "orderId"
        var KEY_SHOULD_SHOW_WORK_DETAILS_FRAGMENT = "shouldShowWorkDetails"

    }

    var sharedPreferenceManger: SharedPreferenceManager? = null
    var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = UserApplication.getAppInstance()
            .getSharedPreferences(
                UserApplication.getAppInstance().getString(R.string.preference_name),
                Context.MODE_PRIVATE
            )
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.commit()
        editor?.apply()
    }

    fun containsKey(key: String): Boolean {
        return sharedPreferences?.contains(key)!!
    }

    fun getString(key: String): String {
        return sharedPreferences?.getString(key, "")!!
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences?.edit()?.putInt(key, value)?.apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getBoolean(key: String): Boolean? {
        return sharedPreferences?.getBoolean(key, false)
    }

    fun saveLong(key: String, value: Long) {
        sharedPreferences?.edit()?.putLong(key, value)?.apply()
    }

    fun getLong(key: String): Long? {
        return sharedPreferences?.getLong(key, 0L)
    }

    fun getInt(key: String): Int {
        return sharedPreferences?.getInt(key, 0)!!
    }

    fun clearData() {
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.commit()
    }

}