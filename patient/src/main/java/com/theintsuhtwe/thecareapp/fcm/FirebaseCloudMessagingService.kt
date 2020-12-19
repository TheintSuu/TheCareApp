package com.theintsuhtwe.thecareapp.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.fragments.BaseFragment
import com.theintsuhtwe.shared.network.response.NotificationVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.activities.MainActivity
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class FirebaseMessagingService : FirebaseMessagingService() {
    companion object{
        const val FCM_CHANNEL_ID = "FCM_CHANNEL_ID"
        const val TAG = "FirebaseService"
        const val NOTIFICATION_ID = 1000
        const val SUMMARY_ID = 1001
    }

    val notificationVO = NotificationVO()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if(remoteMessage.data.isNotEmpty()){
            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
            notificationVO.data?.title = title
            notificationVO.data?.body = body
            createNotification(title,body)
        }
        remoteMessage.notification?.let {
            Log.d(TAG,"${it.body} and ${it.title}")
        }
    }
    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG","onNewToken Called")
        Log.d("Token",token)
        SessionManager.patient_device_token = token
    }

    private fun createNotification(messageTitle:String?,messageBody:String?){
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("notification","yes")
            val bundle = Bundle()
            bundle.putString("dataOne", messageTitle)
            bundle.putString("dataTwo", messageBody)
            this.putExtras(bundle)
        }

        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, FCM_CHANNEL_ID)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "The Care App"
            val descriptionText = "PADC Learning Portal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(FCM_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.apply {
            notify(NOTIFICATION_ID,notificationBuilder)
        }
    }
}