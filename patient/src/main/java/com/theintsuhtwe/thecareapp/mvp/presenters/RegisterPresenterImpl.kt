package com.theintsuhtwe.thecareapp.mvp.presenters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.*
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.RegisterView
import java.util.*

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mPatientModel : PatientModel = PatientModelImpl


    override fun onUiReady(token : String, lifecycleOwner: LifecycleOwner) {

    }

    @SuppressLint("LongLogTag")
    override fun onTapRegister(email: String, password: String, userName: String, token: String) {
        mAuthenticationModel.register(email,password,userName,  onSuccess = {
            mView?.navigateToToLoginScreen()
        },
            onFailure = {
                Log.d("Register Patient Failure", "Failure")
            })


    }




}