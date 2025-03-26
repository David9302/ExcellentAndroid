package com.it.excellent.domain.message

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import java.util.Objects

class PlayerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Objects.equals(intent?.action, Intent.ACTION_MEDIA_BUTTON)) {
            if (intent?.extras == null) {
                return
            }

            val keyEvent = intent.getSerializableExtra(Intent.EXTRA_KEY_EVENT) as KeyEvent
            if (keyEvent == null) {
                return
            }

            if (keyEvent.action != KeyEvent.ACTION_DOWN) {
                return
            }

            when(keyEvent.keyCode) {
                KeyEvent.KEYCODE_HEADSETHOOK, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE ->
                    PlayerMana
            }
        }
    }
}