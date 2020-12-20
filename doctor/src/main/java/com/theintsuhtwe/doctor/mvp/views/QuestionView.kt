package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface QuestionView : BaseView {
    fun displayGeneralQuestions(list : List<QuestionVO>)
    fun navigateToToChatRoom(questions : String)

}