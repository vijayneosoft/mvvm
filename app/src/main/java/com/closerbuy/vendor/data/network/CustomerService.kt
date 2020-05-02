package com.closerbuy.vendor.data.network

import com.closerbuy.vendor.presentation.models.LoginResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Vijay on 7/3/20.
 */
interface CustomerService {

    @GET("vendors/otp/generate/{mobileNumber}")
    fun otpGenerateObservable(
        @Path("mobileNumber") mobileNumber: String
    ): Observable<LoginResponse>


}