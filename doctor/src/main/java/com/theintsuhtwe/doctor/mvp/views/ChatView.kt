package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface ChatView : BaseView {

    fun displayPatientGeneralQuestion(list : ArrayList<QuestionVO>)

    fun displayPatientSpecialQuestion(list : ArrayList<QuestionVO>)

    fun displayPatientChat(list : List<MessageVO>)

    fun navigateToPrescription()

    fun navigateToNote()

    fun navigateToCheckout()

    fun navigateToSpecialQuestionByDoctor()
}