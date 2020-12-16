package com.theintsuhtwe.shared.persistence.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DegreesConverter {
    @TypeConverter
    fun toString(dataList: List<String>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): List<String> {
        val dataListType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }


}