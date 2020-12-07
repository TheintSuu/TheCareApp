package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl

object DoctorModelImpl : DoctorModel{

    private val mFirebase  = CloudFirestoreFirebaseApiImpl


    override fun addDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addDoctor(doctorVO, onSuccess, onFailure)
    }

    override fun getDeviceTokenByType(type: String, onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getDeviceTokenByType(type, onSuccess, onFaiure)
    }

    override fun getDeviceTokenByUser(): String {
        return mFirebase.getDeviceToken()
    }
}