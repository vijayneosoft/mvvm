package com.closerbuy.vendor.presentation.view.login

import androidx.lifecycle.MutableLiveData
import com.closerbuy.vendor.presentation.base.BaseVM
import com.closerbuy.vendor.presentation.models.LoginResponse
import com.closerbuy.vendor.presentation.view.ResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Vijay on 22/3/20.
 */

class LoginVM : BaseVM() {

    var mLoginResponse = MutableLiveData<ResponseModel<LoginResponse>>()

    fun generateMobileNumber(mobileNumber: String) {
        mCustomerService.otpGenerateObservable(mobileNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mLoginResponse.value =
                    ResponseModel.setResponse(
                        it
                    )
            }, {
                mLoginResponse.value =
                    ResponseModel.setError(
                        it
                    )
            })
    }

}