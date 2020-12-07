package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen()
    fun navigateToRegisterScreen()
}