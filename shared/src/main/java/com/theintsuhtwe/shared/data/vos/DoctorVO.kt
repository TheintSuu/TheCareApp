package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doctor")
@IgnoreExtraProperties
data class DoctorVO(
        @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("phone") var phone: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("biography") var biography: String = "",
        @SerializedName("degrees") var degrees : List<String> ?= null,
        @SerializedName("device_token") var device_token: String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("specialities")  var specialities  : String = ""
    )
{
    override fun toString(): String {
        return "DoctorVO(id='$id', name='$name', phone='$phone', email='$email', biography='$biography', degrees=$degrees, device_token='$device_token', image='$image', specialities='$specialities')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DoctorVO

        if (id != other.id) return false
        if (name != other.name) return false
        if (phone != other.phone) return false
        if (email != other.email) return false
        if (biography != other.biography) return false
        if (degrees != other.degrees) return false
        if (device_token != other.device_token) return false
        if (image != other.image) return false
        if (specialities != other.specialities) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + biography.hashCode()
        result = 31 * result + (degrees?.hashCode() ?: 0)
        result = 31 * result + device_token.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + specialities.hashCode()
        return result
    }
}
