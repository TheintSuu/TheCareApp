package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.theintsuhtwe.doctor.mvp.views.LoginView
import com.theintsuhtwe.doctor.utils.saveDoctorToSession
import com.theintsuhtwe.shared.data.models.AuthenticationModel
import com.theintsuhtwe.shared.data.models.AuthenticationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import java.util.*
import kotlin.collections.ArrayList

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    private val mDoctor = DoctorModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapLogin(lifecycleOwner: LifecycleOwner, email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){

        } else {

            mAuthenticatioModel.login(email, password, onSuccess = {
                mDoctor.getDoctorByEmail(email, onSuccess = {
                    saveDoctorToSession(it)
                    mView?.navigateToHomeScreen(it)
//                    mDoctor.getDoctorInfoFromDB(email)
//                        .observe(
//                            lifecycleOwner, Observer {
//                                mView?.navigateToHomeScreen(it)
//                            }
//                        )
//                }, onFailure = {
//
//                })
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