package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
@IgnoreExtraProperties
data class Patient(
        @SerializedName("id") var id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("phone") var phone: String = "",
        @SerializedName("device_token") var device_token: String = "",
        @SerializedName("question") var question: List<QuestionVO> ?= null,
        @SerializedName("address")  var address  : Address ?= null,
        @SerializedName("recent_doctors")  var recent_doctors  : List<DoctorVO> ?= null
)