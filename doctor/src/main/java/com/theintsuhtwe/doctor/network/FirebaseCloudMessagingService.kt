package com.theintsuhtwe.doctor.network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.theintsuhtwe.shared.R
import com.theintsuhtwe.shared.utils.NOTI_TYPE_SEND_BROADCASET_REQUEST
import com.theintsuhtwe.shared.utils.msgType


class FirebaseCloudMessagingService : FirebaseMessagingService() {


    override fun onNewToken(p0: String) {

        //sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {

        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }

    private var msgBody  =  ""
    private var msgType = 1
    private var title : String = ""
    private var orderID : String = " "

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", remoteMessage.data.toString() )

        val body  = remoteMessage.data["body"]
        val title  = remoteMessage.data["title"]

        showNotification(title, body)
    }

    private fun showNotification(title : String?, body : String?){
        val intent  = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT

        )
        val channelId = "Hello_FCM_Channel"
        val channelName = "Hello_FCM_CHANNEL_HI"

        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            setupNotificationChannels(channelId, channelName, notificationManager)
        }

        val soundUri  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)
        notificationManager.notify(0, notificationBuilder.build())
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupNotificationChannels(
        channelId : String,
        channelName : String,
        notificationManager: NotificationManager
    ){
        val channel = NotificationChannel(
            channelId, channelName, NotificationManager.IMPORTANCE_LOW
        )
        channel.enableLights(true)
        channel.lightColor = Color.GREEN
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)
    }
}