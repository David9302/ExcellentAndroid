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
            MainActivity::javaClass



        } catch (e: Exception) {

        }
//        try {
//            String title = testMusic.title;
//            TestAlbum album = PlayerManager.getInstance().getAlbum();
//            String summary = album.summary;
//
//            RemoteViews simpleContentView = new RemoteViews(
//                getApplicationContext().getPackageName(), R.layout.notify_player_small);
//
//            RemoteViews expandedView;
//            expandedView = new RemoteViews(
//                    getApplicationContext().getPackageName(), R.layout.notify_player_big);
//
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.setAction("showPlayer");
//
//            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent,
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : 0);
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                NotificationManager notificationManager = (NotificationManager)
//                getSystemService(Context.NOTIFICATION_SERVICE);
//
//                NotificationChannelGroup playGroup = new NotificationChannelGroup(GROUP_ID, getString(R.string.play));
//                notificationManager.createNotificationChannelGroup(playGroup);
//
//                NotificationChannel playChannel = new NotificationChannel(CHANNEL_ID,
//                    getString(R.string.notify_of_play), NotificationManager.IMPORTANCE_DEFAULT);
//                playChannel.setGroup(GROUP_ID);
//                notificationManager.createNotificationChannel(playChannel);
//            }
//
//            Notification notification = new NotificationCompat.Builder(
//                getApplicationContext(), CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_player)
//                .setContentIntent(contentIntent)
//                .setOnlyAlertOnce(true)
//                .setContentTitle(title).build();
//
//            notification.contentView = simpleContentView;
//            notification.bigContentView = expandedView;
//
//            setListeners(simpleContentView);
//            setListeners(expandedView);
//
//            notification.contentView.setViewVisibility(R.id.player_progress_bar, View.GONE);
//            notification.contentView.setViewVisibility(R.id.player_next, View.VISIBLE);
//            notification.contentView.setViewVisibility(R.id.player_previous, View.VISIBLE);
//            notification.bigContentView.setViewVisibility(R.id.player_next, View.VISIBLE);
//            notification.bigContentView.setViewVisibility(R.id.player_previous, View.VISIBLE);
//            notification.bigContentView.setViewVisibility(R.id.player_progress_bar, View.GONE);
//
//            boolean isPaused = PlayerManager.getInstance().isPaused();
//            notification.contentView.setViewVisibility(R.id.player_pause, isPaused ? View.GONE : View.VISIBLE);
//            notification.contentView.setViewVisibility(R.id.player_play, isPaused ? View.VISIBLE : View.GONE);
//            notification.bigContentView.setViewVisibility(R.id.player_pause, isPaused ? View.GONE : View.VISIBLE);
//            notification.bigContentView.setViewVisibility(R.id.player_play, isPaused ? View.VISIBLE : View.GONE);
//
//            notification.contentView.setTextViewText(R.id.player_song_name, title);
//            notification.contentView.setTextViewText(R.id.player_author_name, summary);
//            notification.bigContentView.setTextViewText(R.id.player_song_name, title);
//            notification.bigContentView.setTextViewText(R.id.player_author_name, summary);
//            notification.flags |= Notification.FLAG_ONGOING_EVENT;
//
//            String coverPath = Const.COVER_PATH + File.separator + testMusic.musicId + ".jpg";
//            Bitmap bitmap = ImageUtils.getBitmap(coverPath);
//
//            if (bitmap != null) {
//                notification.contentView.setImageViewBitmap(R.id.player_album_art, bitmap);
//                notification.bigContentView.setImageViewBitmap(R.id.player_album_art, bitmap);
//            } else {
//                requestAlbumCover(testMusic.coverImg, testMusic.musicId);
//                notification.contentView.setImageViewResource(R.id.player_album_art, R.drawable.bg_album_default);
//                notification.bigContentView.setImageViewResource(R.id.player_album_art, R.drawable.bg_album_default);
//            }
//
//            startForeground(5, notification);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}