package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.LoginView
import com.theintsuhtwe.shared.data.models.AuthenticationModel
import com.theintsuhtwe.shared.data.models.AuthenticationModelImpl
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl



    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapLogin(context: Context, email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){

        } else {

            mAuthenticatioModel.login(email, password, onSuccess = {
                mView?.navigateToHomeScreen(mAuthenticatioModel.getDoctorProfile())
            }, onFailure = {

            })
        }
    }

    override fun onTapRegister() {
        mView?.navigateToRegisterScreen()
    }
}