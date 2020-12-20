package com.theintsuhtwe.shared.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl.mFirebaseApi
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl
import com.theintsuhtwe.shared.network.response.NotiResponse
import com.theintsuhtwe.shared.network.response.NotificationVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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


    @SuppressLint("CheckResult")
    override fun sendNotificationToPatient(
            notificationVO: NotificationVO,
            onSuccess: (notiResponse: NotiResponse) -> Unit,
            onFailure: (String) -> Unit
    ) {

        mApi.sendFcm(notificationVO)
                .map { it }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let { data -> onSuccess(it)
                    }
                }, {
                    onFailure(it.localizedMessage ?: "ERROR MESSAGE")
                })
    }
}