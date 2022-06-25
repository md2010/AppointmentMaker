package com.example.makeappointment.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.R

const val notificationID = 1

class NotificationHelper : BroadcastReceiver() {

    private val CHANNEL_REMINDER = "REMINDER_CHANNEL"
    private val channelID = "channel1"
    private val title = "REMINDER"
    private val message= "You have an appointment at doctor's tomorrow!"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val contentIntent : PendingIntent = PendingIntent.getActivity(context,0, intent, 0)

        val notification = NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.id.icon)
                .setContentTitle(title)
                .setContentIntent(contentIntent)
                .setContentText(message)
                .build()
        Log.d(TAG,"Got here")
        val manager = context.getSystemService(NotificationManager::class.java) as NotificationManager
        manager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context : Context)
    {
        val desc = "Remind me day before an appointment"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelID, CHANNEL_REMINDER, importance)
        channel.description = desc
        channel.setShowBadge(true)
        channel.enableLights(true)
        channel.lightColor = Color.CYAN
        channel.enableVibration(true)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}