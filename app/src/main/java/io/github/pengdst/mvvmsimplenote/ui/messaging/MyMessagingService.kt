package io.github.pengdst.mvvmsimplenote.ui.messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.github.pengdst.mvvmsimplenote.R

/**
 * Created on 6/22/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MyMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        postToken(token)
    }

    private fun postToken(token: String) {

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e("MessageReceived", "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.e("MessageReceived", "Message data payload: ${remoteMessage.data}")
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.e("MessageReceived", "Message Notification Title: ${it.title}")
            Log.e("MessageReceived", "Message Notification Body: ${it.body}")
            buildNotification(it.title, it.body)
        }
    }

    fun buildNotification(title: String?, content: String?) {
        val intent = Intent(this, MessagingActivity::class.java);
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = createNotificationChannel()
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        return NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
            description = CHANNEL_DESCRIPTION
        }
    }

    companion object {
        const val NOTIFICATION_ID = 0
        const val CHANNEL_ID = "channel_0"
        const val CHANNEL_NAME = "channel_name"
        const val CHANNEL_DESCRIPTION = "channel_desc"
    }
}