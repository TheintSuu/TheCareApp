package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName


@IgnoreExtraProperties
data class  MedicineVO(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String ?= null,
    @SerializedName("name") var name: String = "",
    @SerializedName("price")  var price  : Long ?= null,
    @SerializedName("quantity")  var quantity : Long ?= null,
    @SerializedName("sub_total")  var sub_total  : Long ?= null,
    @SerializedName("note")  var note  : String ?= null,
    @SerializedName("repeat")  var repeat  : String ?= null,
    @SerializedName("total_day")  var total_day  : String ?= null,
    @SerializedName("routine") var  routine : String ?= null,
    @SerializedName("tablet") var  tablet : String ?= null,
    @SerializedName("isSelected") var isSelected : Boolean ?= null



)
