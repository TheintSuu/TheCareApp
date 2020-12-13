package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.theintsuhtwe.shared.persistence.typeconverter.QuestionsConverter

@Entity(tableName = "case_summary")
@TypeConverters(QuestionsConverter::class)
@IgnoreExtraProperties
data class CaseSummaryVO(
        @PrimaryKey
        @SerializedName("id") var id : String = " ",
        @SerializedName("questions") var questionList : ArrayList<QuestionVO> = arrayListOf()
        )