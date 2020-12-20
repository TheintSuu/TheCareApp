package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.util.Log
import com.theintsuhtwe.doctor.mvp.views.RegisterFormView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.doctor.utils.saveDoctorToSession
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.shared.utils.randomImage
import java.util.*

class RegisterFormPresenterImpl  :  RegisterFormPresenter, AbstractBasePresenter<RegisterFormView>(){
    private var mDoctorModel = DoctorModelImpl
    override fun onUiReady() {

    }

    override fun onTapSave(email: String, name : String, phone : String, special : String, bd : String, degress : String, bio : String) {


        val degrees : ArrayList<String> = arrayListOf()
        degrees.add(degress)
        val doctor = DoctorVO(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            specialities = special,
            phone = phone,
            birth = bd,
            biography = bio,
            image = randomImage(),
            degrees = degrees,
            device_token = SessionManager.doctor_device_token.toString()
        )
        mDoctorModel.addDoctor(
            doctor,
            onSuccess = {

            },
            onFailure = {
                Log.d("Add Doctor Failure", "Failure")
            }
        )

        mDoctorModel.getDoctorByEmail(
            email,
            onSuccess = {
                saveDoctorToSession(it)
                mView?.navigateToHome()
            },
            onFailure = {

            }
        )

    }
}