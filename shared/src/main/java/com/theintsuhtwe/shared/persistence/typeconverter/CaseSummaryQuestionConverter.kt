package com.theintsuhtwe.shared.persistence.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.theintsuhtwe.shared.data.vos.CaseSummaryQuestion

class CaseSummaryQuestionConverter{
    @TypeConverter
    fun toString(dataList: ArrayList<CaseSummaryQuestion>): String {
        return Gson().toJson(dataList)
    }

    @TypeConverter
    fun toList(ListJsonStr: String): ArrayList<CaseSummaryQuestion> {
        val dataListType = object : TypeToken<ArrayList<CaseSummaryQuestion>>() {}.type
        return Gson().fromJson(ListJsonStr, dataListType)
    }

}