package com.theintsuhtwe.shared.data.models

import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.network.response.NotiResponse
import com.theintsuhtwe.shared.network.response.NotificationVO

interface DoctorModel {

    fun  addDoctor(doctorVO: DoctorVO,onSuccess:()->Unit,onFailure:(String)->Unit)

    fun getDoctorByEmail(id : String, onSuccess: (doctor : DoctorVO) -> Unit, onFailure: (String) -> Unit)

    fun getDeviceTokenByType(type : String,onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByUser() : String


    fun addDoctorInfoToDB(doc : DoctorVO)


    fun getDoctorInfoFromDB(email : String) : LiveData<DoctorVO>

    fun sendNotificationToPatient(
            notificationVO: NotificationVO,
            onSuccess: (notiResponse: NotiResponse) -> Unit,
            onFailure: (String) -> Unit
    )
}