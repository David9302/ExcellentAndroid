package com.it.excellent.architecture.data.response.manager

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.it.excellent.common.utils.Utils

class NetworkStateManager : DefaultLifecycleObserver {

    companion object {
        private val S_MANAGER = NetworkStateManager()

        private val mNetworkStateReceive = NetworkStateReceive()

        fun getInstance(): NetworkStateManager {
            return S_MANAGER
        }
    }

    /**
     * todo tip : 让NetworkStateManager 可观察页面生命周期.
     * 从而在页面失去焦点时, 及时断开本页面对网络状态的监测，以避免重复回调和一系列不可预期的问题.
     *
     * 关于 Lifecycle 组件的存在意义，可详见《为你还原一个真实的 Jatpack Lifecycle》篇的解析.
     * https://xiaozhuanlan.com/topic/3684721950
     */
    override fun onResume(owner: LifecycleOwner) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        Utils.getApp().applicationContext.registerReceiver(mNetworkStateReceive, filter)
    }

    override fun onPause(owner: LifecycleOwner) {
        Utils.getApp().applicationContext.unregisterReceiver(mNetworkStateReceive)
    }
}