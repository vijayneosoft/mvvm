package com.closerbuy.vendor.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.closerbuy.vendor.presentation.view.ResponseModel
import com.nanosoft.linie.dataProvider.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Vijay on 23/3/20.
 */

open class BaseVM : ViewModel() {

    var mCustomerService = ApiService.getInstance().call()


}