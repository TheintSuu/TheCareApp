package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class ConsultationRequest(
        @SerializedName("id") var id: String = "",
        @SerializedName("consultation_chat_id") var consultationId: String ?= null,
        @SerializedName("case_summary") var caseSummary : ArrayList<QuestionVO> ?= arrayListOf(),
        @SerializedName("patient") var patient: Patient ?= null,
        @SerializedName("doctor") var doctor: DoctorVO ?= null,
        @SerializedName("specialities")  var specialities  : String ?= null,
        @SerializedName("status")  var status  : String ?= null
)