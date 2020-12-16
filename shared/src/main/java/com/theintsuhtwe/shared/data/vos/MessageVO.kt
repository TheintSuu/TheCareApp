package com.theintsuhtwe.shared.data.vos

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName


@IgnoreExtraProperties
data class MessageVO(
        @SerializedName("id") var id: String = "",
        @SerializedName("sender_id") var senderId: String ?= "",
        @SerializedName("sender_image") var senderImage: String ?= "",
        @SerializedName("text") var text: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("send_time") var sendTime : String = ""

)