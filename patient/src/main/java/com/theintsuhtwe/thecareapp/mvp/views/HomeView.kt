package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface HomeView : BaseView {

    fun displaySpecialities(category: List<CategoryVO>)

    fun displayDoctorBySpecialities(doctor : List<DoctorVO>)

    fun displayConsultationConfirm(doctor : DoctorVO)

    fun displayQuestions()

    fun navigateToQuestion(id : String, category : String)

    fun navigateToChat(id : String)

    fun showConfirmDialog(id : String)

    fun showRecentConfirmDialog(id : String, doctorId : String)

    fun showConsultationRecevied(consulation: ConsultationRequest)

    fun displayRecentDoctorList(doctors : List<DoctorVO>)







}