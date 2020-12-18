package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen()
    fun navigateToRegisterScreen()
    fun navigateToRegisterForm(email : String)
}