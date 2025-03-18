package com.it.excellent.common.utils

import android.content.res.Configuration

object ScreenUtils {

    /**
     * 判断屏幕是否是竖屏.
     */
    fun isPortrait(): Boolean {
        return Utils.getApp().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
}