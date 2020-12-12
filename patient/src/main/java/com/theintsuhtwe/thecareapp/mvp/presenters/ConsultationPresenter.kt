package com.theintsuhtwe.thecareapp.mvp.presenters

import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView

interface ConsultationPresenter  :  BasePresenter<CaseSumaryQuestionView>,
        SpecialitiesItemDelegate {
}