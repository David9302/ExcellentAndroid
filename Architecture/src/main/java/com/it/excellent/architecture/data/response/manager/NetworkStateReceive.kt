package com.it.excellent.architecture.data.response.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.it.excellent.architecture.R
import com.it.excellent.common.utils.NetworkUtils
import java.util.Objects

class NetworkStateReceive : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Objects.equals(intent?.action, ConnectivityManager.EXTRA_NO_CONNECTIVITY)) {
            if (!NetworkUtils.isConnected()) {
                Toast.makeText(context, context?.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show()
            }
        }
    }
}