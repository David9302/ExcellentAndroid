package com.it.excellent.ui.widget

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.IBinder
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.it.excellent.MainActivity
import com.it.excellent.R
import com.it.excellent.data.bean.TestAlbum
import com.it.excellent.domain.proxy.PlayerManager
import com.it.excellent.domain.usercase.DownloadUseCase
import java.io.File

class PlayerService : Service() {

    companion object {
        const val NOTIFY_PREVIOUS: String = "pure_music.kunminx.previous"
        const val NOTIFY_CLOSE: String = "pure_music.kunminx.close"
        const val NOTIFY_PAUSE: String = "pure_music.kunminx.pause"
        const val NOTIFY_PLAY: String = "pure_music.kunminx.play"
        const val NOTIFY_NEXT: String = "pure_music.kunminx.next"
        const val GROUP_ID: String = "group_001"
        const val CHANNEL_ID: String = "channel_001"
        private var mDownloadUseCase: DownloadUseCase? = null
    }

    private fun createNotification(testMusic: TestAlbum.TestMusic) {
        try {
            val title = testMusic.title
            val album = PlayerManager.getInstance().album
            val summary = album.summary

            val simpleContentView = RemoteViews(applicationContext.packageName, R.layout.notify_player_small)
            var expandedView: RemoteViews? = RemoteViews(applicationContext.packageName, R.layout.notify_player_big)

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.action = "showPlayer"

        } catch (e: Exception) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}