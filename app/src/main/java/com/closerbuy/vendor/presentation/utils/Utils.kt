package com.closerbuy.vendor.presentation.utils

import android.os.CountDownTimer
import android.widget.TextView

/**
 * Created by Vijay on 23/4/20.
 */

class Utils {

    companion object {
        fun countDownTimer(seconds: Long, txtView: TextView): CountDownTimer {
            val countDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
                override fun onFinish() {

                }

                override fun onTick(millisUntilFinished: Long) {

                }
            }
            return countDownTimer
        }
    }


}