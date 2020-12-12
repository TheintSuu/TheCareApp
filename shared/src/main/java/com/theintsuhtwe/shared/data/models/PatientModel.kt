package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.*

interface PatientModel {
    fun  addPatient(patient: Patient, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun  getPatient(id : String, onSuccess:(patient: Patient)->Unit, onFailure:(String)->Unit)

    fun getRecentDoctors(id : String, onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByType(type : String,onSuccess: (List<String>) -> Unit, onFaiure: (String) -> Unit)

    fun getDeviceTokenByUser() : String

    fun sendQuestion(id : String, ques : List<QuestionVO>, onSuccess: () -> Unit, onFaiure: (String) -> Unit)

    fun getQuestionByPatient(id : String,  onSuccess: (ques : List<QuestionVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getCaseSummaryByPatient(id : String,  onSuccess: (ques : CaseSummaryVO) -> Unit, onFaiure: (String) -> Unit)



}