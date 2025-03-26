package com.it.excellent.architecture.ui.page

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import com.it.excellent.architecture.data.response.manager.NetworkStateManager
import com.it.excellent.common.utils.AdaptScreenUtils
import com.it.excellent.common.utils.BarUtils
import com.it.excellent.common.utils.ScreenUtils
import com.kunminx.architecture.ui.page.DataBindingActivity
import com.kunminx.architecture.ui.scope.ViewModelScope

abstract class BaseActivity : DataBindingActivity() {

    companion object {
        private val mViewModelScope: ViewModelScope = ViewModelScope()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(this, true)
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(NetworkStateManager.getInstance())
        //TODO tip 1: DataBinding 严格模式（详见 DataBindingActivity - - - - - ）：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这方式，彻底解决 View 实例 Null 安全一致性问题，
        // 如此，View 实例 Null 安全性将和基于函数式编程思想的 Jetpack Compose 持平。

        // 如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
    }

    /**
     * tip 2： Jetpack 通过工厂模式 实现 ViewModel 作用于可控
     */
    protected fun <T : ViewModel> getActivityScopeViewModel(modelCLass: Class<T>): T {
        return mViewModelScope.getActivityScopeViewModel(this, modelCLass)
    }

    protected fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        return mViewModelScope.getApplicationScopeViewModel(modelClass)
    }


    override fun getResources(): Resources {
        return if (ScreenUtils.isPortrait()) {
            AdaptScreenUtils.adaptWidth(super.getResources(), 360)
        } else {
            AdaptScreenUtils.adaptHeight(super.getResources(), 640)
        }
    }

    protected fun toggleSoftInput() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    protected fun openUrlInBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}