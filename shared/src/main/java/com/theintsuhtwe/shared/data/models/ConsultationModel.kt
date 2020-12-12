package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.*

interface ConsultationModel {

    fun getConsultationByPatient(id : String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationRequestByDoctor(special : String, onSuccess: (List<ConsultationRequest>) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationConfirmByPatient(id : String, onSuccess: (ConsultationRequest) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationByDoctor(id : String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addConsultation(consultation : ConsultationVO,  onSuccess:()->Unit, onFailure:(String)->Unit)

    fun sendMessageBySenderType(id : String, message : MessageVO, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun addPrescriptionByDoctor(id : String, medicines : List<MedicineVO>, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun deletePrescriptionByDoctor(id : String, medicines : List<MedicineVO>, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun createCaseSummary(patient: Patient, special: String,list : List<QuestionVO>,  onSuccess:(id : String)->Unit, onFailure:(String)->Unit )

    fun sendConsultationRequest(patient :  Patient, special: String, caseSummaryVO: String, onSuccess: (id : String) -> Unit, onFailure: (String) -> Unit)

}