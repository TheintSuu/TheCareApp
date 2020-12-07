package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class ConsultationRequest(
        @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String = "",
        @SerializedName("case_summary") var caseSummary : CaseSummaryVO ?= null,
        @SerializedName("patient") var patient: Patient ?= null,
        @SerializedName("doctor") var doctor: DoctorVO ?= null,
        @SerializedName("specialities")  var specialities  : CategoryVO ?= null
)