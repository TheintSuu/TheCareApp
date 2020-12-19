package com.theintsuhtwe.shared.data.models

import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.*

interface PatientModel {
    fun  addPatient(patient: Patient, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun  addRecentDoctor(id: String, doctors: DoctorVO, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun  getPatient(id : String, onSuccess:(patient: Patient)->Unit, onFailure:(String)->Unit)

    fun  getPatientByEmail(email  : String, onSuccess:(patient: Patient)->Unit, onFailure:(String)->Unit)

    fun getRecentDoctors(id : String, onSuccess: () -> Unit, onFaiure: (String) -> Unit)

    fun getAllRecentDoctorsFromDB( ) : LiveData<List<DoctorVO>>

    fun getRecentDoctorsFromDB(id : String) : LiveData<DoctorVO>

    fun insertRecentDoctorsFromDB(doctors : List<DoctorVO>)

    fun insertRecenFromDB(doctors : DoctorVO)



    fun getDeviceTokenByType(type : String,onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByUser() : String

    fun sendQuestion(id : String, ques : List<QuestionVO>, onSuccess: () -> Unit, onFaiure: (String) -> Unit)

    fun getQuestionByPatient(id : String,  onSuccess: (ques :  ArrayList<QuestionVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getCaseSummaryByPatient(id : String,  onSuccess: (ques : CaseSummaryVO) -> Unit, onFaiure: (String) -> Unit)

    fun getPatientByEmailFromDB(email: String)  : LiveData<Patient>

    fun getOnceTimeQuestion(id: String, name: String, bdd: String, phone: String, onSuccess : () -> Unit, onFailure : ()  -> Unit)




}