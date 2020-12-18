package com.theintsuhtwe.shared.network.auth

import android.graphics.Bitmap
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient


interface AuthManager {
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun loginWithFacebook(email: String, publicProfile: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun register(email: String, password: String, userName: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun registerDoctor(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getUserProfile() : Patient
    fun updateProfileUrl(photoUrl: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit)
    fun uploadProfileUrl(bitmap: Bitmap,onSuccess: (imgUrl:String) -> Unit,onFailure: (String) -> Unit)
    fun getDoctorProfile() : DoctorVO
    fun updateDoctorProfileUrl(photoUrl: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit)
    fun uploadDoctorProfileUrl(bitmap: Bitmap,onSuccess: (imgUrl:String) -> Unit,onFailure: (String) -> Unit)


}