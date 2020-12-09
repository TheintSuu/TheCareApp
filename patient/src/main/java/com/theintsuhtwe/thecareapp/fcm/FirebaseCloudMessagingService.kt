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
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.fragments.BaseFragment
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException

class FirebaseCloudMessagingService :  FirebaseMessagingService() {

    private lateinit var mPresenter: HomePresenter


    override fun onNewToken(p0: String) {

        //sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }



    private var msgBody  =  ""
    private var msgType = 1
    private var title : String = ""
    private var orderID : String = " "

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", remoteMessage.data.toString() )

        val name  = remoteMessage.data["name"]
        val title  = remoteMessage.data["specialities"]
        val biography  = remoteMessage.data["biography"]

        val doctorVO = title?.let {
            biography?.let { it1 ->
                name?.let { it2 ->
                    DoctorVO(
                        name = it2, specialities = it, biography = it1
                    )
                }
            }
        }


    }


    private fun sendConfirmReceived(){

    }


}