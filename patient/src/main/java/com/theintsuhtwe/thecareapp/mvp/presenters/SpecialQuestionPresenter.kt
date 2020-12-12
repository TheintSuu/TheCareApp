package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView
import com.theintsuhtwe.thecareapp.mvp.views.SpecialQuestionView

interface SpecialQuestionPresenter :   BasePresenter<SpecialQuestionView>,
        SpecialitiesItemDelegate {

    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapContinue(list : List<QuestionVO>)

}