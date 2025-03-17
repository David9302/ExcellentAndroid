package com.it.excellent.common.utils

import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity

object BarUtils {

    @JvmStatic
    fun setStatusBarColor(
        activity: AppCompatActivity,
        @ColorInt color: Int
    ): View? {
        return BarUtils.setStatusBarColor(activity, color, false)
    }

    @JvmStatic
    fun setStatusBarColor(
        activity: AppCompatActivity,
        @ColorInt color: Int,
        isDecor: Boolean
    ): View? {
//        BarUtils.transparentStatusBar(activity)
//        return BarUtils.applyStatusBarColor(activity, color, isDecor)
        return null
    }

    @JvmStatic
    fun setStatusBarLightMode(
        activity: AppCompatActivity,
        isLightMode: Boolean
    ) {
//        setStatusBarLightMode(activity.window, isLightMode)
    }
}