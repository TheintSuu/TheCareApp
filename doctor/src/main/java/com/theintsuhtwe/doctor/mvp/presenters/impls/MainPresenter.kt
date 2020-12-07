package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner

interface MainPresenter {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapStartConsultation()
}