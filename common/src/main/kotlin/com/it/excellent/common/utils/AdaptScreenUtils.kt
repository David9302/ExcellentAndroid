package com.it.excellent.common.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import java.lang.reflect.Field

object AdaptScreenUtils {

    private var isInitMiui = false
    private var mTmpMeticsField:Field? = null

    fun adaptHeight(resources: Resources, designHeight: Int): Resources {
        val dm = getDisplayMetrics(resources)
        val newXdpi = (dm?.widthPixels?.times(72f))?.div(designHeight)

    }

    @JvmStatic
    fun getDisplayMetrics(resources: Resources): DisplayMetrics? {
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
                    mTmpMeticsField = Resources::class.java.getDeclaredField("mTmpMetrics")
                    mTmpMeticsField!!.isAccessible = true
                    ret = mTmpMeticsField!!.get(resources) as DisplayMetrics
                } catch (e: Exception) {
                    Log.e("AdaptScreenUtils", "no field of mTmpMetrics in resources")
                }
            }
            isInitMiui = true
            return ret
        }

        if (mTmpMeticsField == null) {
            return null
        }

        try {
            return mTmpMeticsField?.get(resources) as DisplayMetrics
        } catch (e: Exception) {
            return null
        }
    }
}