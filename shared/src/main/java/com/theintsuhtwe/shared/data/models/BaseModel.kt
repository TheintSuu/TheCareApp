package com.theintsuhtwe.shared.data.models

import android.content.Context
import com.google.gson.GsonBuilder
import com.theintsuhtwe.shared.network.ApiService
import com.theintsuhtwe.shared.persistence.db.TheCareDB
import com.theintsuhtwe.shared.utils.FirebaseURL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract  class BaseModel {

    protected var mApi: ApiService

    lateinit var mTheCareDB: TheCareDB
    init {
        val mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(FirebaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()

        mApi = retrofit.create(ApiService::class.java)
    }

    fun initDatabase(context: Context) {
        mTheCareDB = TheCareDB.getDBInstance(context)
    }
}