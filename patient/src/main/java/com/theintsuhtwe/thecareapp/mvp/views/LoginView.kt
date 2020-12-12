package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen(patient : Patient)
    fun navigateToRegisterScreen()
}