package com.theintsuhtwe.shared.network.response

import com.google.gson.annotations.SerializedName

data class NotiResponse(
    @SerializedName("success") val success: Int,
    @SerializedName("failure") val failure: Int
)