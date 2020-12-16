package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {

    fun onUiReady(tooken : String, lifecycleOwner: LifecycleOwner)

    fun onTapRegister(lifecycleOwner: LifecycleOwner, email:String,password:String,userName:String, token : String)

}