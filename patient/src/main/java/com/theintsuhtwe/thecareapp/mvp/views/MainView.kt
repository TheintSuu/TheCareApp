package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface MainView : BaseView {

    fun displaySpecialities(category: List<CategoryVO>)

    fun displayDoctorBySpecialities(doctor : List<DoctorVO>)

    fun displayConsultationConfirm(doctor : DoctorVO)

    fun displayQuestions(qustions : List<QuestionVO>)


    fun displayRecentDoctorList(doctors : List<DoctorVO>)







}