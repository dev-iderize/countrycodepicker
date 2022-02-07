package com.techjays.techjayscountrycodepicker.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.sj.covidradar.util.AppDialogs
import java.text.DecimalFormat

object Utility {

    private val df = DecimalFormat(".##########")

    fun formatDecimal(value: Double): String {
        return df.format(value)
    }

    @SuppressLint("MissingPermission")
    fun isInternetAvailable(context: Context?): Boolean {
        try {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo: NetworkInfo? = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return false
    }

    fun checkInternet(context: Context): Boolean {
        return if (isInternetAvailable(context))
            true
        else {
            AppDialogs.okAction(
                context,
                "Oops, No internet connection"
            )
            false
        }
    }



}