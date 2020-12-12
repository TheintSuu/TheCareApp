package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView

interface CaseSummaryPresenter :   BasePresenter<CaseSummaryView>,
        SpecialitiesItemDelegate {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapSendConsultationRequest(special : String, patient : Patient, list : List<QuestionVO>)
}