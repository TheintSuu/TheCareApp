package com.theintsuhtwe.shared.data.models

import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl

object PatientModelImpl: PatientModel,  BaseModel() {

    private val mFirebase  = CloudFirestoreFirebaseApiImpl


    override fun addPatient(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addPatient(patient, onSuccess, onFailure)
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
            mTheCareDB.patientDao.deleteAll()
            mTheCareDB.patientDao.insertpatient(it)
            onSuccess(it)
        }, onFailure={

        })
    }

    override fun getPatientByEmailFromDB(email: String)  : LiveData<Patient> {
        return mTheCareDB.patientDao.getpatientByEmail(email)

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

    override fun sendQuestion(id: String, ques: List<QuestionVO>, onSuccess: () -> Unit, onFaiure: (String) -> Unit) {
        mFirebase.sendQuestion(id, ques, onSuccess , onFaiure)
    }

    override fun getQuestionByPatient(id: String,  onSuccess: (ques: ArrayList<QuestionVO>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getQuestionsByPateint(id,  onSuccess , onFaiure)
    }

    override fun getCaseSummaryByPatient(id: String, onSuccess: (ques: CaseSummaryVO) -> Unit, onFaiure: (String) -> Unit) {
       return mFirebase.getCaseSummaryByPatient(id, onSuccess, onFaiure)
    }


}