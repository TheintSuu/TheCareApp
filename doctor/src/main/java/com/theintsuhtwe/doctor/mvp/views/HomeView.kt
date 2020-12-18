package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface HomeView : BaseView {

//    fun displaySpecialQuestionByCateogry(category: List<QuestionVO>)
//    fun displayGeneralQuestionByCateogry(category: List<QuestionVO>)
//    fun displayMedicineQuestionByCateogry(category: List<MedicineVO>)
    fun displayConsultationHistory(history : List<ConsultationVO>)
    fun displayRecentConsultation(history : List<ConsultationRequest>)
    fun displayConsultationConfirm(history : ConsultationRequest)
    fun displayConsultationRequest(request : List<ConsultationRequest>)
    fun displayEmptyView()
    fun removeConsultationRequest(request : ConsultationRequest)
    fun displayPrescriptionDialog(id : String, list : List<MedicineVO>)
    fun showDialog()
        fun showDialogNote(note : String)
    fun navigateToLogin()
    fun  navigateToChatActivity(id : String)
    fun  navigateToNoteActivity(id : String)
    fun  navigateToChatHistoryActivity(id : String, type : String)


}