package com.theintsuhtwe.shared.data.models

import android.graphics.Bitmap
import android.util.Log
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.network.auth.AuthManager
import com.theintsuhtwe.shared.network.auth.AuthenticationManagerImpl
import com.theintsuhtwe.shared.utils.randomImage
import java.util.*

object AuthenticationModelImpl : AuthenticationModel {
    override var mAuthManager: AuthManager = AuthenticationManagerImpl

    private var mPatientModel = PatientModelImpl

    override fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.login(email,password,onSuccess = {
            onSuccess()
        }, onFailure = {

        })
    }

    override fun loginWithFacebook(
        email: String,
        public_profile: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun register(email: String, password: String, userName: String, token : String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.register(email,password,userName,onSuccess ={
            val patientVO = Patient(
                    id = UUID.randomUUID().toString(),
                    name = userName,
                    email = email,
                    image = randomImage(),
                    device_token = token
            )
            mPatientModel.addPatient(patientVO, onSuccess, onFailure)

        },onFailure= onFailure)
    }

    override fun registerDoctor(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.registerDoctor(email,password, onSuccess = {
            onSuccess()
        },onFailure= {

        })
    }

    override fun getUserProfile(): Patient {
        return mAuthManager.getUserProfile()
    }

    override fun updateUserProfile(imgUrl: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.updateProfileUrl(imgUrl,onSuccess,onFailure)
    }

    override fun uploadPhotoUrl(bitmap: Bitmap, onSuccess: (imgUrl: String) -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.uploadProfileUrl(bitmap,onSuccess,onFailure)
    }

    override fun getDoctorProfile(): DoctorVO {
       return mAuthManager.getDoctorProfile()
    }

    override fun updateDoctorProfile(
        imgUrl: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.updateDoctorProfileUrl(imgUrl,onSuccess,onFailure)
    }

    override fun uploadDoctorPhotoUrl(
        bitmap: Bitmap,
        onSuccess: (imgUrl: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.uploadDoctorProfileUrl(bitmap,onSuccess,onFailure)
    }
}