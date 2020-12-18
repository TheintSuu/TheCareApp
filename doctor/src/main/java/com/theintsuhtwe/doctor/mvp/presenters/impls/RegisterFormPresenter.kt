package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.RegisterFormView
import com.theintsuhtwe.doctor.mvp.views.RegisterView
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface RegisterFormPresenter  : BasePresenter<RegisterFormView> {

    fun onUiReady()

    fun onTapSave(email: String, name : String, phone : String, special : String, bd : String, degress : String, bio : String)

}