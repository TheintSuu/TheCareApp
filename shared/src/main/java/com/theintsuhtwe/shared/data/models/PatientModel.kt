package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient

interface PatientModel {
    fun  addPatient(patient: Patient, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun getRecentDoctors(id : String, onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByType(type : String,onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByUser() : String



}