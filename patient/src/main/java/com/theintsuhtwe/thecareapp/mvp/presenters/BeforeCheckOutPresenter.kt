package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CheckOutDisplayView
import com.theintsuhtwe.thecareapp.mvp.views.CheckoutView

interface BeforeCheckOutPresenter : BasePresenter<CheckOutDisplayView> {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapCheckOut(id : String)


}