package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl

object PatientModelImpl: PatientModel{

    private val mFirebase  = CloudFirestoreFirebaseApiImpl


    override fun addPatient(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addPatient(patient, onSuccess, onFailure)
    }

    override fun getRecentDoctors(id: String, onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit) {
       return mFirebase.getRecentlyDoctorByUser(id, onSuccess, onFaiure)
    }

    override fun getDeviceTokenByType(type: String, onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getDeviceTokenByType(type, onSuccess, onFaiure)
    }

    override fun getDeviceTokenByUser(): String {
       return mFirebase.getDeviceToken()
    }



}