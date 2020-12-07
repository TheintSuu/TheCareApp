package com.theintsuhtwe.thecareapp.activities

//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.drawable.Drawable
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.widget.RemoteViews
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import androidx.core.content.ContextCompat
//import com.theintsuhtwe.shared.activities.BaseActivity
//import com.theintsuhtwe.thecareapp.R
//
//class NotificationActivity  : BaseActivity() {
//
//    val CHANNel_ID = "NOTIFICATION_CHANNEL"
//    val EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        createNotificationChannel()
//
//        val notificationId = 1998
//        val notificationId02 = 1999
//        val notificationId03 = 2000
//        val notificationId04 = 2001
//        val notificationId05 = 2002
//        val notificationId06 = 2003
//
//        val textTitle = "Hello Friend"
//        val textContent = "I am a New Notification"
//
//
//        val builder = NotificationCompat.Builder(this, CHANNel_ID)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle(textTitle)
//            .setContentText(textContent)
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                .bigText("Much longer text that cannot fit one line..."))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        btnCreate.setOnClickListener {
//            with(NotificationManagerCompat.from(this)){
//                notify(notificationId, builder.build())
//            }
//
//        }
//
//        //Notification with Intent
//        val intent = Intent(this, SecondActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        val pendingIntent : PendingIntent = PendingIntent.getActivity(
//            this, 0, intent, 0
//        )
//
//        val builder2 = NotificationCompat.Builder(this, CHANNel_ID)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle(textTitle)
//            .setContentText(textContent)
//            .setContentIntent(pendingIntent)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        btnIntent.setOnClickListener {
//            with(NotificationManagerCompat.from(this)){
//                notify(notificationId02, builder2.build())
//            }
//        }
//
//
//
//
//
//
//
//
//
//
//
//        FirebaseInstanceId.getInstance().instanceId
//            .addOnCompleteListener(OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.w("Token", "getInstanceId failed", task.exception)
//                    return@OnCompleteListener
//                }
//
//                // Get new Instance ID token
//                val token = task.result?.token
//
//                // Log and toast
//                val msg ="token :  $token"
//                Log.i("Token ", msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//            })
//    }
//
//
//    private fun createNotificationChannel(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val name = "PADC Myanmar"
//            val descriptionText = "PADC Learning Portal"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNel_ID, name, importance).apply {
//                description  = descriptionText
//            }
//
//            val notificationManager : NotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//
//        }
//    }
//
//
//}