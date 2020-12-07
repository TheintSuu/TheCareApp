package com.theintsuhtwe.shared.data.vos

import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class CheckOutVO(
        @PrimaryKey(autoGenerate = true)
         @SerializedName("id") var id: String = "",
        @SerializedName("patient") var patient : Patient ?= null,
        @SerializedName("address") var address : Address ?= null,
        @SerializedName("prescription") var  medicine: List<MedicineVO> ?= null,
        @SerializedName("total_amount") var  total_amount: Long = 0,
        @SerializedName("delivery_routine") var  delivery_routine : DeliveryRoutine?= null
)
