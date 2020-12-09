package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.HomeView

interface HomePresenter : BasePresenter<HomeView>, SpecialitiesItemDelegate {
    fun onUiReady(lifecycleOwner: LifecycleOwner)

    fun onTapCategory(id : String)

    fun onTapConsultationRequest(case : CaseSummaryVO, patient: Patient, specail: CategoryVO)

//    fun onTapStartConsultation(case: CaseSummaryVO, patient: Patient, doctorVO: DoctorVO, specail : CategoryVO)
//
//    fun onTapNextQuestion(id : String)

    fun onTapConfirm(id : String, category : String)

    fun onTapCancel()
}