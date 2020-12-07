package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class Address(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("state") var state: String = "",
        @SerializedName("township") var township: String = "",
        @SerializedName("street") var street: String = ""
)
