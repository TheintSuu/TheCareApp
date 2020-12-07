package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "medicine")
@IgnoreExtraProperties
data class  MedicineVO(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("price")  var price  : Long = 0,
    @SerializedName("quantity")  var quantity  :Long = 0,
    @SerializedName("sub_total")  var sub_total  :Long = 0,
    @SerializedName("note")  var note  : String = "",
    @SerializedName("repeat")  var repeat  : String = "",
    @SerializedName("routine") var  routine : RoutineVO?= null


)
