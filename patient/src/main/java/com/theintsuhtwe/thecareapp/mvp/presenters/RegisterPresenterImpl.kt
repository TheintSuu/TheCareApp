package com.theintsuhtwe.thecareapp.mvp.presenters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.*
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.RegisterView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import java.util.*

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mPatientModel : PatientModel = PatientModelImpl

    private val mDoctor = DoctorModelImpl


    override fun onUiReady(token : String, lifecycleOwner: LifecycleOwner) {

    }

    @SuppressLint("LongLogTag")
    override fun onTapRegister(lifecycleOwner: LifecycleOwner, email: String, password: String, userName: String) {
        mAuthenticationModel.register(email,password,userName, SessionManager.patient_device_token.toString(), onSuccess = {

            mView?.navigateToToLoginScreen()
        },
            onFailure = {

            })


    }




}