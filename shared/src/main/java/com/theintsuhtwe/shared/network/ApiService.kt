package com.theintsuhtwe.shared.network

import com.theintsuhtwe.shared.network.response.NotiResponse
import com.theintsuhtwe.shared.network.response.NotificationVO
import io.reactivex.Observable
import retrofit2.http.*
import com.theintsuhtwe.shared.utils.SERVER_KEY

interface ApiService {
    @Headers(
            "Content-Type:application/json",
            "Authorization:$SERVER_KEY"
    )
    @POST("fcm/send")
    fun sendFcm(@Body notificationVO: NotificationVO) : Observable<NotiResponse>


}