package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen(doctorVO: DoctorVO)
    fun navigateToRegisterScreen()
}