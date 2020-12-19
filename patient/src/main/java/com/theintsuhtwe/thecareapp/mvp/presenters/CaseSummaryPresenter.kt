package com.theintsuhtwe.thecareapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView

interface CaseSummaryPresenter :   BasePresenter<CaseSummaryView>,
        SpecialitiesItemDelegate {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapSendConsultationRequest(context : Context, special: String, list: ArrayList<QuestionVO>, lifecycleOwner: LifecycleOwner)
}