package com.it.excellent.common.utils

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity

object BarUtils {

    private const val TAG_STATUS_BAR = "TAG_STATUS_BAR"
    private const val TAG_OFFSET = "TAG_OFFSET"
    private const val KEY_OFFSET = -123

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
        transparentStatusBar(activity)
        return applyStatusBarColor(activity, color, isDecor)
    }

    @JvmStatic
    fun setStatusBarLightMode(
        activity: AppCompatActivity,
        isLightMode: Boolean
    ) {
        setStatusBarLightMode(activity.window, isLightMode)
    }

    @JvmStatic
    fun setStatusBarLightMode(window: Window, isLightMode: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            if (decorView != null) {
                var vis = decorView.systemUiVisibility
                if (isLightMode) {
                    vis = vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    vis = vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                decorView.systemUiVisibility = vis
            }
        }
    }

    @JvmStatic
    private fun applyStatusBarColor(activity: AppCompatActivity, color: Int, isDecor: Boolean):View {
        val parent = if (isDecor) {
            activity.window.decorView as ViewGroup
        } else {
            activity.findViewById<ViewGroup>(android.R.id.content)!!
        }
        var fakeStatusBarView = parent.findViewWithTag<View>(TAG_STATUS_BAR)
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.visibility == View.GONE) {
                fakeStatusBarView.visibility = View.VISIBLE
            }
            fakeStatusBarView.setBackgroundColor(color)
        } else {
            fakeStatusBarView = createStatusBarView(activity, color)
            parent.addView(fakeStatusBarView)
        }
        return fakeStatusBarView
    }

    @JvmStatic
    private fun createStatusBarView(activity: AppCompatActivity, color: Int): View {
        val statusBarView = View(activity)
        statusBarView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        statusBarView.setBackgroundColor(color)
        statusBarView.setTag(TAG_STATUS_BAR)
        return statusBarView
    }

    @JvmStatic
    fun getStatusBarHeight(): Int {
        val resources = Resources.getSystem()
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    @JvmStatic
    private fun transparentStatusBar(activity: AppCompatActivity) {
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val vis = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = option or vis
        } else {
            window.decorView.systemUiVisibility = option
        }
        window.statusBarColor = Color.TRANSPARENT

    }
}