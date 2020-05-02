package com.closerbuy.vendor.presentation.base

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.closerbuy.vendor.R
import com.closerbuy.vendor.data.SharedPreferenceManager
import com.closerbuy.vendor.data.network.CustomerService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.nanosoft.linie.dataProvider.ApiService
import kotlinx.android.synthetic.main.toolbar_dashboard.*
import pub.devrel.easypermissions.EasyPermissions
import java.util.*


/**
 * Created by Vijay on 21/3/20.
 */
abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    val mSharedManager = SharedPreferenceManager()
    var mCustomerService: CustomerService? = null
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val mBaseVM: BaseVM by viewModels()
    var mVendorId = ""
    protected var requiredPermitsGranted = true
    var dialog: AlertDialog? = null
    val gson = Gson()


    private val LOCATION_PERMS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    protected open fun getPermissionList(): List<String> {
        return ArrayList()
    }

    protected open fun doActionBackPress() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mCustomerService = ApiService.getInstance().call()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mVendorId =
            mSharedManager.getString(SharedPreferenceManager.KEY_VENDOR_ID)

    }

    fun showAlertDialog(title: String, message: String) {
//        val isActivityFinishing = (applicationContext as Activity).isFinishing
//        if (applicationContext != null && !isActivityFinishing) {
        val builder = AlertDialog.Builder(this, R.style.UserAlertDialog)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(
            "OK",
            { dialog, which ->
                doActionBackPress()
            })
        builder.setNegativeButton(
            "Cancel",
            { dialog, which ->
                dialog.dismiss()
            })
        val dialog = builder.create()
        dialog.show()
//        }
    }


    fun showToastMessage(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun setToolbarWithTitle(title: String) {

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            val mToolBarTitle = toolbar.findViewById<TextView>(R.id.txtTitle_home_tl)
            mToolBarTitle.setText(title)
        }
    }


    fun showBackArrow() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun hideBackArrow() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun showProgress(context: Context) {

        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setView(R.layout.dialog_progress)
        dialog = builder.create()
        dialog!!.show()
    }

    fun hideProgress() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    fun replaceFragment(@IdRes containerViewId: Int, @NonNull fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(containerViewId, fragment)
            .commit()
    }

}