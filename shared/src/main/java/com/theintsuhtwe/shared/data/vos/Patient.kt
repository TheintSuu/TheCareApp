package com.theintsuhtwe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.theintsuhtwe.shared.persistence.typeconverter.AddressConverter
import com.theintsuhtwe.shared.persistence.typeconverter.DoctorConverter
import com.theintsuhtwe.shared.persistence.typeconverter.QuestionsConverter

@Entity(tableName = "patient")
@IgnoreExtraProperties
@TypeConverters(QuestionsConverter::class, DoctorConverter::class, AddressConverter::class)
data class Patient(
        @PrimaryKey
        @SerializedName("id") var id: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("birthday") var bDate : String = "",
        @SerializedName("image") var image: String = "",
        @SerializedName("phone") var phone: String = "",
        @SerializedName("device_token") var device_token: String = "",
        @SerializedName("question") var question: ArrayList<QuestionVO> = arrayListOf(),
        @SerializedName("address")  var address  : ArrayList<Address> = arrayListOf(),
        @SerializedName("recent_doctors")  var recent_doctors  : ArrayList<DoctorVO> = arrayListOf()
)