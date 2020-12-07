package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.DoctorVO

interface DoctorModel {

    fun  addDoctor(doctorVO: DoctorVO,onSuccess:()->Unit,onFailure:(String)->Unit)


    fun getDeviceTokenByType(type : String,onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByUser() : String
}