package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.theintsuhtwe.shared.persistence.typeconverter.QuestionsConverter


@IgnoreExtraProperties
data class CaseSummaryQuestion(
        @PrimaryKey
        @SerializedName("id") var id: String = "",
        @SerializedName("description") var description: String = "",
        @SerializedName("type") var type: String = "",
        @SerializedName("answer") var answer: String ?= null

)