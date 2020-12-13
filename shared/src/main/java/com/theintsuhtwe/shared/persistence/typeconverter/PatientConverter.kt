package com.theintsuhtwe.shared.persistence.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.theintsuhtwe.shared.data.vos.Patient

class PatientConverter {
    @TypeConverter
    fun toString(dataList: Patient): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): Patient {
        val dataListType = object : TypeToken<Patient>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}