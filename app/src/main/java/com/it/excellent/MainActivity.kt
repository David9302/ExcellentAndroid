package com.it.excellent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.it.excellent.architecture.ui.page.BaseActivity
import com.it.excellent.architecture.ui.page.StateHolder
import com.it.excellent.ui.theme.ExcellentAndroidTheme
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.state.State

class MainActivity : BaseActivity() {

    private var mStates: MainActivityStates? = null
//    private val mMessager: PageMes
    private var mIsListened = false

    override fun initViewModel() {
        mStates = getActivityScopeViewModel(MainActivityStates::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig {
        TODO("Not yet implemented")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    /**
     *
     */
    class MainActivityStates : StateHolder() {

        val isDrawerOpened:State<Boolean> = State<Boolean>(false)
        val openDrawer: State<Boolean> = State<Boolean>(false)
        val allowDrawerOpen: State<Boolean> = State<Boolean>(false)
    }
}