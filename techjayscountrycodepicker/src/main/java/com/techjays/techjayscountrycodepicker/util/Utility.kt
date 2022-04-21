package com.techjays.techjayscountrycodepicker.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import com.sj.covidradar.util.AppDialogs
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.techjays.techjayscountrycodepicker.R
import java.io.File
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

    fun loadUserImage(aURL: String?, image: ImageView) {
        val placeHolder = R.drawable.download
        try {
            if (aURL.isNullOrEmpty()) {
                image.setImageResource(placeHolder)
            } else {
                if (aURL.contains("http")) {
                    Picasso.get().load(aURL)
                        .placeholder(placeHolder)
                        .error(placeHolder)
                        .fit().centerCrop()
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(image)
                } else {
                    Picasso.get().load(File(aURL))
                        .placeholder(placeHolder)
                        .error(placeHolder)
                        .fit().centerCrop()
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(image)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}