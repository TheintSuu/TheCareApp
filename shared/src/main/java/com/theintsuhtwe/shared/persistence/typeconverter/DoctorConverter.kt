package com.theintsuhtwe.shared.persistence.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.theintsuhtwe.shared.data.vos.DoctorVO

class DoctorConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<DoctorVO>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<DoctorVO> {
        val dataListType = object : TypeToken<ArrayList<DoctorVO>>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}