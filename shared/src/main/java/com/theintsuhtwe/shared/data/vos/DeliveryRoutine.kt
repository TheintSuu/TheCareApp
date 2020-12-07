package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class DeliveryRoutine(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("date") var date: String = "",
        @SerializedName("time") var time: Timestamp ?= null
)
