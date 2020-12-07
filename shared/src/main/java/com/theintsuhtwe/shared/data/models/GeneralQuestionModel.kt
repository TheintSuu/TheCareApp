package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.QuestionVO

interface GeneralQuestionModel {
    fun getQuestionsBySpecaility(id:String,onSuccess:(List<QuestionVO>)->Unit,onFailure:(String)->Unit)

    fun getQuestions(onSuccess:(List<QuestionVO>)->Unit,onFailure:(String)->Unit)

    fun getQuestionsByType(type : String, onSuccess:(List<QuestionVO>)->Unit,onFailure:(String)->Unit)
}