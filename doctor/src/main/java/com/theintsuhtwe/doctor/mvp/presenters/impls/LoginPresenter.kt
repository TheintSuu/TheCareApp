package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.LoginView
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface LoginPresenter : BasePresenter<LoginView> {
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapLogin(context : Context, email: String, password: String)
    fun onTapRegister()
}