package com.it.excellent.common.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import java.lang.reflect.Field
import java.util.UUID

object AdaptScreenUtils {

    private var isInitMiui = false
    private var mTmpMetricsField:Field? = null

    @JvmStatic
    fun adaptWidth(resources: Resources, designWidth: Int): Resources {
        val dm = getDisplayMetrics(resources)
        val newXdpi = (dm.widthPixels.times(72f)).div(designWidth)
        dm.xdpi = newXdpi
        setAppDmXdpi(newXdpi)
        return resources
    }

    @JvmStatic
    fun adaptHeight(resources: Resources, designHeight: Int): Resources {
        val dm = getDisplayMetrics(resources)
        val newXdpi = (dm.widthPixels.times(72f)).div(designHeight)
        dm.xdpi = newXdpi
        setAppDmXdpi(newXdpi)
        return resources
    }

    @JvmStatic
    private fun setAppDmXdpi(xdpi: Float) {
        Utils.getApp().resources.displayMetrics.xdpi = xdpi
    }

    @JvmStatic
    fun getDisplayMetrics(resources: Resources): DisplayMetrics {
        val miuiDisplayMetrics = getMiuiTmpMetrics(resources) ?: return resources.displayMetrics
        return miuiDisplayMetrics
    }

    @JvmStatic
    fun getMiuiTmpMetrics(resources: Resources): DisplayMetrics? {
        if (!isInitMiui) {
            var ret: DisplayMetrics? = null
            val simpleName = resources.javaClass.simpleName
            if ("MiuiResources" == simpleName || "XResources" == simpleName) {
                try {
                    mTmpMetricsField = Resources::class.java.getDeclaredField("mTmpMetrics")
                    mTmpMetricsField!!.isAccessible = true
                    ret = mTmpMetricsField!!.get(resources) as DisplayMetrics
                } catch (e: Exception) {
                    Log.e("AdaptScreenUtils", "no field of mTmpMetrics in resources")
                }
            }
            isInitMiui = true
            return ret
        }

        if (mTmpMetricsField == null) {
            return null
        }

        try {
            return mTmpMetricsField?.get(resources) as DisplayMetrics
        } catch (e: Exception) {
            return null
        }
    }
}