package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface ChatView : BaseView {

    fun displayPatientGeneralQuestion(list : ArrayList<QuestionVO>)

    fun displayPatientSpecialQuestion(list : ArrayList<QuestionVO>)

    fun displayPatientChat(list : List<MessageVO>)

    fun navigateToPrescription(special : String)

    fun navigateToNote()

    fun navigateToCheckout()

    fun showSpeciality(id : String)

    fun navigateToSpecialQuestionByDoctor()

    fun displayPrescription(list :List<MedicineVO>)

    fun navigateToCheckOut(id : String)
}