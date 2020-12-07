package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class SenderType(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")  val id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("image") var  image: String = ""
)
