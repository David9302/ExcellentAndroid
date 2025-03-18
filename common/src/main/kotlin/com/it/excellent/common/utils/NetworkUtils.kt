package com.it.excellent.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {

    @JvmStatic
    fun isConnected(): Boolean {
        val info = getActiveNetworkInfo()
        return info != null && info.isConnected
    }

    private fun getActiveNetworkInfo(): NetworkInfo? {
        val cm = Utils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm == null) {
            return null
        }
        return cm.activeNetworkInfo
    }
}