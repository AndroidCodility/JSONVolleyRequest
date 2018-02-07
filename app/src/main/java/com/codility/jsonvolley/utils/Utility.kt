package com.codility.jsonvolley.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by Govind on 2/6/2018.
 */
object Utility {

    @SuppressLint("MissingPermission")
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}