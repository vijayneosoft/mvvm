package com.closerbuy.vendor.presentation.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Vijay on 3/12/19.
 */

class LoginResponse {

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("otp")
    @Expose
    var otp: String? = null

}