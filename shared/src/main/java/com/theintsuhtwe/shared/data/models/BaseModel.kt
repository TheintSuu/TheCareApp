package com.theintsuhtwe.shared.data.models

import android.content.Context
import com.google.gson.GsonBuilder
import com.theintsuhtwe.shared.network.NotificationApi
import com.theintsuhtwe.shared.persistence.db.TheCareDB
import com.theintsuhtwe.shared.utils.FirebaseURL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract  class BaseModel {

    protected var mNotiApi: NotificationApi

    lateinit var mTheCareDB: TheCareDB
    init {
        val client =  OkHttpClient.Builder()
          .connectTimeout(15, TimeUnit.SECONDS)
          .readTimeout(15, TimeUnit.SECONDS)
          .writeTimeout(15, TimeUnit.SECONDS)
          .build()
      val gson = GsonBuilder()
          .setLenient()
          .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(FirebaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

        mNotiApi = retrofit.create(NotificationApi::class.java)
    }

    fun initDatabase(context: Context) {
        mTheCareDB = TheCareDB.getDBInstance(context)
    }
}