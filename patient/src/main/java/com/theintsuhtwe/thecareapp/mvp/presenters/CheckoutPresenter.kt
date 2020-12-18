package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

import com.theintsuhtwe.thecareapp.mvp.views.CheckoutView

interface CheckoutPresenter : BasePresenter<CheckoutView> {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapCheckOut(id : String)


}