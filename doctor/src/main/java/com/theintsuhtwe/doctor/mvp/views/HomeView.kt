package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface HomeView : BaseView {

    fun displaySpecialQuestionByCateogry(category: List<QuestionVO>)
    fun displayGeneralQuestionByCateogry(category: List<QuestionVO>)
    fun displayMedicineQuestionByCateogry(category: List<MedicineVO>)
    fun displayConsultationHistory(history : List<ConsultationVO>)
    fun displayConsultationConfirm(history : ConsultationRequest)
    fun displayConsultationRequest(request : List<ConsultationRequest>)
    fun showDialog()
}