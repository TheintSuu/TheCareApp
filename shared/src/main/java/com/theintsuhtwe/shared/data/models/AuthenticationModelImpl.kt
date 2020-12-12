package com.theintsuhtwe.shared.data.models

import android.graphics.Bitmap
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.network.auth.AuthManager
import com.theintsuhtwe.shared.network.auth.AuthenticationManagerImpl

object AuthenticationModelImpl : AuthenticationModel {
    override var mAuthManager: AuthManager = AuthenticationManagerImpl

    override fun login(email: String, password: String, onSuccess: (patient : Patient) -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.login(email,password,onSuccess = onSuccess,onFailure = onFailure)
    }

    override fun loginWithFacebook(
        email: String,
        public_profile: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun register(email: String, password: String, userName: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.register(email,password,userName,onSuccess =onSuccess,onFailure= onFailure)
    }

    override fun registerDoctor(
        email: String,
        password: String,
        userName: String,
        category: CategoryVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.registerDoctor(email,password,userName, category, onSuccess =onSuccess,onFailure= onFailure)
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