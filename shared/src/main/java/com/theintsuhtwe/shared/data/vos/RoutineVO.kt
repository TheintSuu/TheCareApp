package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RoutineVO(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("date")  val count : String = "",
    @SerializedName("duration") var duration:  String = "",
    @SerializedName("time") var time:  List<String> ?= null
)