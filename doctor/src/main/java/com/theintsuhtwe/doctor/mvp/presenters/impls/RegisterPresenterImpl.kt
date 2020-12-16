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
        password: String,
        userName: String,
        categoryVO: CategoryVO
    ) {
        mAuthenticationModel.registerDoctor(email,password,userName, categoryVO, onSuccess = {
            val degrees : ArrayList<String> = arrayListOf()
            degrees.add("M.B.B.S(Y.G.N)")
            val doctor = DoctorVO(
                id = UUID.randomUUID().toString(),
                name = "Professor U Aung Win",
                email = email,
                specialities = "Dental",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjNUOgjEhHpfUqnVk-Tp2uN1AhrrzXhwdX9A&usqp=CAU",
                degrees = degrees,
                device_token = ""
            )
            mDoctor.addDoctor(
                doctor,
                onSuccess = {

                },
                onFailure = {
                    Log.d("Add Doctor Failure", "Failure")
                }
            )


        },
            onFailure = {
                Log.d("Register Doctor Failure", "Failure")
            })


    }


}