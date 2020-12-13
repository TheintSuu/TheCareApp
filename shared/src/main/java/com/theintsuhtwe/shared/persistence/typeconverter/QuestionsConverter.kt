package com.theintsuhtwe.shared.persistence.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.theintsuhtwe.shared.data.vos.QuestionVO

class QuestionsConverter {
    @TypeConverter
fun toString(dataList: ArrayList<QuestionVO>): String {
    return Gson().toJson(dataList)
}

@TypeConverter
fun toList(ListJsonStr: String): ArrayList<QuestionVO> {
    val dataListType = object : TypeToken<ArrayList<QuestionVO>>() {}.type
    return Gson().fromJson(ListJsonStr, dataListType)
}

}