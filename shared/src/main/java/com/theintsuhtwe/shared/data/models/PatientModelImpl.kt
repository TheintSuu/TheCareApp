package com.theintsuhtwe.shared.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl
import com.theintsuhtwe.shared.network.response.NotiResponse
import com.theintsuhtwe.shared.network.response.NotificationVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PatientModelImpl: PatientModel,  BaseModel() {

    private val mFirebase  = CloudFirestoreFirebaseApiImpl


    override fun addPatient(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addPatient(patient, onSuccess, onFailure)
    }

    override fun addRecentDoctor(id: String, doctors: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       return mFirebase.addRecentDoctorByPatient(id, doctors, onSuccess, onFailure)
    }

    override fun getPatient(
        id: String,
        onSuccess: (patient: Patient) -> Unit,
        onFailure: (String) -> Unit
    ) {
       return mFirebase.getPatient(id, onSuccess, onFailure)
    }

    override fun getPatientByEmail(email: String, onSuccess: (patient: Patient) -> Unit, onFailure: (String) -> Unit) {

        return mFirebase.getPatientByEmail(email, onSuccess={
         //   mTheCareDB.patientDao.deleteAll()
           // mTheCareDB.patientDao.insertpatient(it)
            onSuccess(it)
        }, onFailure={

        })
    }

    override fun getPatientByEmailFromDB(email: String)  : LiveData<Patient> {
        return mTheCareDB.patientDao.getpatientByEmail(email)

    }

    override fun getOnceTimeQuestion(id: String, name: String, bdd: String, phone: String, onSuccess : () -> Unit, onFailure : ()  -> Unit) {
        mFirebase.getOnceTimeQuestion(id, name, bdd, phone, onSuccess, onFailure)
    }



    override fun getRecentDoctors(id: String, onSuccess: () -> Unit, onFaiure: (String) -> Unit) {
       return mFirebase.getRecentlyDoctorByUser(id, onSuccess={
           mTheCareDB.patientDao.deleteAll()
           insertRecentDoctorsFromDB(it)
           onSuccess()
       }, onFailure = {
           onFaiure(it)
       })
    }

    override fun getAllRecentDoctorsFromDB(): LiveData<List<DoctorVO>> {
      return mTheCareDB.doctorDao().getAllDoctor()
    }

    override fun getRecentDoctorsFromDB(id: String): LiveData<DoctorVO> {
        return mTheCareDB.doctorDao().getDoctorByemail(id)
    }

    override fun insertRecentDoctorsFromDB(doctors: List<DoctorVO>) {
       mTheCareDB.doctorDao().deleteAll()
        mTheCareDB.doctorDao().insertAllDoctor(doctors)
    }

    override fun insertRecenFromDB(doctors: DoctorVO) {
        mTheCareDB.doctorDao().insertDoctor(doctors)
    }


    override fun getDeviceTokenByType(type: String, onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit) {
      //  return mFirebase.getDeviceTokenByType(type, onSuccess, onFaiure)
    }

    override fun getDeviceTokenByUser(): String {
       return mFirebase.getDeviceToken()
    }

    override fun sendQuestion(id: String, ques: List<QuestionVO>, onSuccess: () -> Unit, onFaiure: (String) -> Unit) {
        mFirebase.sendQuestion(id, ques, onSuccess , onFaiure)
    }

    override fun getQuestionByPatient(id: String,  onSuccess: (ques: ArrayList<QuestionVO>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getQuestionsByPateint(id,  onSuccess , onFaiure)
    }

    override fun getCaseSummaryByPatient(id: String, onSuccess: (ques: CaseSummaryVO) -> Unit, onFaiure: (String) -> Unit) {
       return mFirebase.getCaseSummaryByPatient(id, onSuccess, onFaiure)
    }

    @SuppressLint("CheckResult")
    override fun sendBroadcastToDoctor(notificationVO: NotificationVO, onSuccess: (notiResponse: NotiResponse) -> Unit, onFailure: (String) -> Unit) {


        mApi.sendFcm(notificationVO)
                .map { it }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let { data ->
                        onSuccess(it)
                    }
                }, {
                    onFailure(it.localizedMessage ?: "ERROR MESSAGE")
                })
    }


}