package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "case_summary")
@IgnoreExtraProperties
data class CaseSummaryVO(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id") var id: String = "",
        @SerializedName("questions") var questionList : List<QuestionVO> ?= null
        )