package com.theintsuhtwe.doctor.mvp.presenters.impls

import com.theintsuhtwe.doctor.delegates.QuestionDelegate
import com.theintsuhtwe.doctor.mvp.views.QuestionView
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface QuestionPresenter : BasePresenter<QuestionView>, QuestionDelegate {
    fun onUiReady(special : String)
}