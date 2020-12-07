package com.theintsuhtwe.shared.network

import com.theintsuhtwe.shared.network.response.NotiResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface NotificationApi {
    @POST("/fcm/send")
    @Headers("Content-Type: application/json")
    fun sendBroadCastRequestToDoctor(
        @Body data : JSONObject
    ) : Observable<NotiResponse>
}