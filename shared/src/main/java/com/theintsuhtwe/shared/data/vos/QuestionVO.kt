package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.theintsuhtwe.shared.persistence.typeconverter.QuestionsConverter

@Entity(tableName = "questions")
@IgnoreExtraProperties
data class QuestionVO(
        @PrimaryKey
    @SerializedName("id") var id: String = "",
    @SerializedName("description") var description: String ?= null,
    @SerializedName("type") var type: String = "",
    @SerializedName("answer") var answer:  String ?= null

)
