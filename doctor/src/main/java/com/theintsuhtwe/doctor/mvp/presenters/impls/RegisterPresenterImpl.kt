package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.RegisterView
import com.theintsuhtwe.shared.data.models.AuthenticationModel
import com.theintsuhtwe.shared.data.models.AuthenticationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModel
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import java.util.*

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mDoctor = DoctorModelImpl


    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

    }

    override fun onTapRegister(
        email: String,
        password: String

    ) {
        mAuthenticationModel.registerDoctor(email,password, onSuccess = {
            mView?.navigateToToLoginScreen()


        },
            onFailure = {
                Log.d("Register Doctor Failure", "Failure")
            })

    }

}