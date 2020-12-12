package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "question")
@IgnoreExtraProperties
data class QuestionVO(
//    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("type") var type: String = "",
    @SerializedName("answer") var answer: String ?= null

)
