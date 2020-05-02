package com.closerbuy.vendor.presentation.utils

import java.text.SimpleDateFormat

/**
 * Created by Vijay on 27/3/20.
 */

class DateAndTimeUtils {

    companion object {

        fun getTimeInHourAndMinutes(value: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            val outputFormat = SimpleDateFormat("HH mm ")
            val date = inputFormat.parse(value)
            val formattedDate = outputFormat.format(date)
            val value = formattedDate.split(" ")
            val valueFirst = value.get(0).toInt()
            val valueSecond = value.get(0)
            if (valueFirst > 12) {
                val first = valueFirst - 12
                return first.toString() + " : " + valueSecond + " PM"
            } else {
                return valueFirst.toString() + " : " + valueSecond + " AM"
            }

        }

        fun convertMinToHour(valueInMin: Int): String {
            val hour = valueInMin / 60
            val minutes = valueInMin % 60
            val calculatedValue = "" + hour + " hr" + " : " + minutes + " min"
            return calculatedValue

        }

    }
}