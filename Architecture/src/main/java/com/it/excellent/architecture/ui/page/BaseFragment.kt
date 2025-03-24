package com.it.excellent.architecture.ui.page

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.it.excellent.common.utils.Utils
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.page.DataBindingFragment
import com.kunminx.architecture.ui.scope.ViewModelScope

abstract class BaseFragment : DataBindingFragment() {

    private val mViewModelScope = ViewModelScope()

    /**
     * 将 dataBinding 实例限制于 Base 页面中，默认不向子类暴露.
     * 通过这方法，彻底解决 View 实例 Null 安全一致性问题，
     * 如此，View 实例 nul 安全性和基于函数式编程思想的jetpack compose 持平.
     */

    // 如这么说无体会，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

    // TODO tip 2： Jetckpack 通过 ”工厂模式“ 实现 ViewModel 作用于可控.
    // 目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域.
    // 值得注意的是：通过不通作用域 Provider 获得 ViewModel 实例并非同一个.
    // 固若 ViewModel 状态信息保留不符合预期，可从该角度出发排查 是否跟前 ViewModel 实例飞目标实例所致.

    // 如这么说无体会，详见 https://xiaozhuanlan.com/topic/6257931840

    protected  fun <T : ViewModel> getFragmentScopeViewModel(modelClass: Class<T>): T {
        return mViewModelScope.getFragmentScopeViewModel(this, modelClass)
    }

    protected fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T {
        return mViewModelScope.getActivityScopeViewModel(mActivity, modelClass)
    }

    protected fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        return mViewModelScope.getApplicationScopeViewModel(modelClass)
    }

    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    protected fun openUrlInBrowser(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    protected fun toggleSoftInput() {
        val imm = mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    protected fun getApplicatioonContext(): Context {
        return mActivity.applicationContext
    }

}