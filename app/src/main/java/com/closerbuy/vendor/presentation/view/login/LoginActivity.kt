package com.closerbuy.vendor.presentation.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.closerbuy.vendor.R
import com.closerbuy.vendor.presentation.base.BaseActivity
import com.closerbuy.vendor.presentation.view.ResponseModel
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by Vijay on 7/3/20.
 */

class LoginActivity : BaseActivity(), View.OnClickListener {

    val mLoginVM: LoginVM by viewModels()
    var mobileNum = ""

    companion object {
        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener(this)
        txtBackMobileNumber.setOnClickListener(this)

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnLogin -> {

                if (edtUsername.text.isNotEmpty()) {
                    if (btnLogin.text.toString().equals(getString(R.string.next))) {
                        mobileNum = edtUsername.text.toString()

                        if (mobileNum.length == 10) {

                            mLoginVM.generateMobileNumber(mobileNum)
                            mLoginVM.mLoginResponse.observe(this, Observer {
                                if (it != null) {
                                    when (it.status) {
                                        ResponseModel.SUCCESS -> {
                                            txtBackMobileNumber.visibility = View.VISIBLE
                                            edtUsername.text.clear()
                                            edtUsername.hint = getString(R.string.enter_otp)
                                            btnLogin.text = getString(R.string.login)
                                            edtUsername.setFilters(
                                                arrayOf<InputFilter>(
                                                    LengthFilter(
                                                        6
                                                    )
                                                )
                                            )
                                            showToastMessage(it.mResponse?.message)
                                        }
                                        ResponseModel.ERROR -> {
                                            Log.d("ERROR", "" + it.error?.message)
                                        }
                                    }
                                }
                            })
                        } else {
                            showToastMessage(getString(R.string.error_valid_number))
                        }
                    } else {
                        // callVerifyOtp
                    }
                } else {
                    Toast.makeText(this, getString(R.string.field_cannot_empty), Toast.LENGTH_SHORT)
                        .show()
                }
            }
            R.id.txtBackMobileNumber -> {
                txtBackMobileNumber.visibility = View.GONE
                imgVerified.visibility = View.GONE
                edtUsername.text.clear()
                edtUsername.hint = getString(R.string.enter_new_number)
                btnLogin.text = getString(R.string.next)
                edtUsername.setFilters(arrayOf<InputFilter>(LengthFilter(10)))
                mobileNum = ""
            }
        }
    }


}