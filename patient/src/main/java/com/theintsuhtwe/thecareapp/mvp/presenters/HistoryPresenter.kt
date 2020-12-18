package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.HistoryDelegate
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.ChatView
import com.theintsuhtwe.thecareapp.mvp.views.ConsultationHistoryView
import com.theintsuhtwe.thecareapp.views.viewpod.EmptyViewPod

interface HistoryPresenter : BasePresenter<ConsultationHistoryView>, HistoryDelegate, EmptyViewPod.Delegate {

    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onDialogUiReady(id: String)





}