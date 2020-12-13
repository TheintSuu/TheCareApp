package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName


@IgnoreExtraProperties
data class ConsultationVO(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id") var id: String = "",
        @SerializedName("patient") var patient: Patient ?= null,
        @SerializedName("doctor") var doctor: DoctorVO ?= null,
        @SerializedName("chats") var message : List<MessageVO>  = arrayListOf(),
        @SerializedName("prescription") var medicine : List<MedicineVO> = arrayListOf(),
        @SerializedName("case_summary") var caseSummary : ArrayList<QuestionVO> = arrayListOf()

)
