package com.it.excellent.architecture.ui.page

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.it.excellent.common.utils.BarUtils
import com.kunminx.architecture.ui.page.DataBindingActivity
import com.kunminx.architecture.ui.scope.ViewModelScope

public abstract class BaseActivity : DataBindingActivity() {

    companion object {
        private val mViewModelScope: ViewModelScope = ViewModelScope()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(this, true)
        super.onCreate(savedInstanceState)

//        lifecycle.addObserver(Networkstate)
    }
}