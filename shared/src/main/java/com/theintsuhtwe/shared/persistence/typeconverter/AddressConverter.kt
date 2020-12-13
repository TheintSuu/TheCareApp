package com.theintsuhtwe.shared.persistence.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.theintsuhtwe.shared.data.vos.Address

class AddressConverter {
    @TypeConverter
    fun toString(dataList: ArrayList<Address>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<Address> {
        val dataListType = object : TypeToken<ArrayList<Address>>(){}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }
}