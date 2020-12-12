package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface HomePresenter : BasePresenter<HomeView>, ConsultationItemDelegate  {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapStartConsultation()
}