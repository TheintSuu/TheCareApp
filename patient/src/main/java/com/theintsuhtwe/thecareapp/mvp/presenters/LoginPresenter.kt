package com.theintsuhtwe.thecareapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView> {
    fun onUiReady(token : String, lifecycleOwner: LifecycleOwner)
    fun onTapLogin(context : Context, email: String, password: String)
    fun onTapRegister()
}