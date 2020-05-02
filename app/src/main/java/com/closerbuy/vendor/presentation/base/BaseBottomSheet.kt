package com.closerbuy.vendor.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.closerbuy.vendor.data.SharedPreferenceManager
import com.closerbuy.vendor.data.network.CustomerService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nanosoft.linie.dataProvider.ApiService

/**
 * Created by Vijay on 8/2/20.
 */

abstract class BaseBottomSheet : BottomSheetDialogFragment() {

    var mCustomerService: CustomerService? = null
    var mSharedPreferenceManager: SharedPreferenceManager? = null

    init {
        mSharedPreferenceManager = SharedPreferenceManager()
        mCustomerService = ApiService.getInstance().call()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = LayoutInflater.from(activity).inflate(getLayout(), container, false)
        return view
    }

    abstract fun getLayout(): Int

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun showToastMessage(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}