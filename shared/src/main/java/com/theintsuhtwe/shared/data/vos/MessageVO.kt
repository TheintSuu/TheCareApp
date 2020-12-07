package com.theintsuhtwe.shared.data.vos

import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName


@IgnoreExtraProperties
data class MessageVO(
        @SerializedName("id") var id: String = "",
        @SerializedName("senderType") var senderType: SenderType ?= null,
        @SerializedName("text") var text: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("send_time") var sendTime : String = ""

)