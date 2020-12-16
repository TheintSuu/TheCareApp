package com.theintsuhtwe.shared.data.models

import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl

object DoctorModelImpl : DoctorModel, BaseModel(){

    private val mFirebase  = CloudFirestoreFirebaseApiImpl


    override fun addDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addDoctor(doctorVO, onSuccess ={
           onSuccess()
       }, onFailure ={
            onFailure(it)
       })
    }

    override fun getDoctorByEmail(email: String, onSuccess: (doctor : DoctorVO) -> Unit, onFailure: (String) -> Unit) {
       mFirebase.getDoctorByEmail(email, onSuccess= {
//           mTheCareDB.doctorDao().deleteAll()
//           mTheCareDB.doctorDao().insertDoctor(it)
           onSuccess(it)

       },
       onFailure = {
            onFailure(it)
       })
    }


    override fun getDeviceTokenByType(type: String, onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getDeviceTokenByType(type, onSuccess, onFaiure)
    }

    override fun getDeviceTokenByUser(): String {
        return mFirebase.getDeviceToken()
    }

    override fun addDoctorInfoToDB(doc: DoctorVO) {
       mTheCareDB.doctorDao().insertDoctor(doc)
    }

    override fun getDoctorInfoFromDB(email: String): LiveData<DoctorVO> {
        return mTheCareDB.doctorDao().getDoctorByemail(email)
    }
}