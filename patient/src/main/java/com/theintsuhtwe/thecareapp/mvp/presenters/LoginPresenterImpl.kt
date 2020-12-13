package com.theintsuhtwe.thecareapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.AuthenticationModel
import com.theintsuhtwe.shared.data.models.AuthenticationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModel
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl


    private val mPatientModel : PatientModel = PatientModelImpl

    override fun onUiReady(token : String, lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapLogin(context: Context, email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){

        } else {
            mAuthenticatioModel.login(email, password, onSuccess = {
                mPatientModel.getPatientByEmail(email, onSuccess = {
                    mView?.navigateToHomeScreen(patient = it)
                }, onFailure = {

                })
            }, onFailure = {

            })


        }
    }

    override fun onTapRegister() {
        mView?.navigateToRegisterScreen()
    }
}