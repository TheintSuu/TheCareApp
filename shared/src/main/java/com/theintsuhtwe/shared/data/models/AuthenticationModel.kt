package com.theintsuhtwe.shared.data.models

import android.graphics.Bitmap
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.network.auth.AuthManager

interface AuthenticationModel {

    var mAuthManager : AuthManager

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun loginWithFacebook(email: String, public_profile: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
        email: String,
        password: String,
        userName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun registerDoctor(
        email: String,
        password: String,
        userName: String,
        category: CategoryVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserProfile(): Patient

    fun updateUserProfile(imgUrl: String,onSuccess: (String) -> Unit,onFailure: (String) -> Unit)


    fun uploadPhotoUrl(bitmap: Bitmap, onSuccess: (imgUrl:String) -> Unit, onFailure: (String) -> Unit)


    fun getDoctorProfile(): DoctorVO

    fun updateDoctorProfile(imgUrl: String,onSuccess: (String) -> Unit,onFailure: (String) -> Unit)


    fun uploadDoctorPhotoUrl(bitmap: Bitmap, onSuccess: (imgUrl:String) -> Unit, onFailure: (String) -> Unit)
}