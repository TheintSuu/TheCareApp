package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.theintsuhtwe.shared.persistence.typeconverter.DegreesConverter

@Entity(tableName = "doctor")
@TypeConverters(DegreesConverter::class)
@IgnoreExtraProperties
data class DoctorVO(
        @PrimaryKey
        @SerializedName("id") var id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("phone") var phone: String = "",
        @SerializedName("bd") var birth: String = "",
        @SerializedName("email") var email: String ?= null,
        @SerializedName("biography") var biography: String = "",
        @SerializedName("degrees") var degrees : List<String> ?= arrayListOf(),
        @SerializedName("device_token") var device_token: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("specialities")  var specialities  : String = ""
    )
{

}
