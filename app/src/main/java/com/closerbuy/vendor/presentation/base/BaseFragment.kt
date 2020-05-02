package com.closerbuy.vendor.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.closerbuy.vendor.data.SharedPreferenceManager

/**
 * Created by Vijay on 4/4/20.
 */

abstract class BaseFragment : Fragment() {

    val mSharedManager = SharedPreferenceManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(activity).inflate(getLayout(), container, false)
        return view
    }

    abstract fun getLayout(): Int

}