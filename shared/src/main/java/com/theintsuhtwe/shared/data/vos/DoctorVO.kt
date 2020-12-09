package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doctor")
@IgnoreExtraProperties
data class DoctorVO(
        @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("phone") var phone: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("biography") var biography: String = "",
        @SerializedName("degrees") var degrees : List<String> ?= null,
        @SerializedName("device_token") var device_token: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("specialities")  var specialities  : String = ""
    )
