package com.theintsuhtwe.doctor.delegates

import com.theintsuhtwe.shared.data.vos.QuestionVO

interface QuestionDelegate {
    fun onTapOneQuestion(generalQuestionTemplateVO: QuestionVO)
}