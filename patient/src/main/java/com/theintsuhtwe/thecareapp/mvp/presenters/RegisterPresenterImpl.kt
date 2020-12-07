package com.theintsuhtwe.thecareapp.mvp.presenters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.*
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mPatientModel : PatientModel = PatientModelImpl


    override fun onUiReady(token : String, lifecycleOwner: LifecycleOwner) {

    }

    @SuppressLint("LongLogTag")
    override fun onTapRegister(
        email: String,
        password: String,
        userName: String
    ) {
        mAuthenticationModel.register(email,password,userName,  onSuccess = {
            Log.d("Register Patient", "Success")

        },
            onFailure = {
                Log.d("Register Patient Failure", "Failure")
            })

        mPatientModel.addPatient(
            Patient(id = "Patient001",
          name = userName, image = "", email = email
        ),
            onSuccess = {
                Log.d("Add Doctor", "Success")
            },
            onFailure = {
                Log.d("Add Doctor Failure", "Failure")
            }
        )
    }


}