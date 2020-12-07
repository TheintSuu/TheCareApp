package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.*

interface ConsultationModel {

    fun getConsultationByPatient(id : String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationByDoctor(id : String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addConsultation(consultation : ConsultationVO,  onSuccess:()->Unit, onFailure:(String)->Unit)

    fun sendMessageBySenderType(id : String, message : MessageVO, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun addPrescriptionByDoctor(id : String, medicines : List<MedicineVO>, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun deletePrescriptionByDoctor(id : String, medicines : List<MedicineVO>, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun createCaseSummary(case : CaseSummaryVO,  onSuccess:()->Unit, onFailure:(String)->Unit )

    fun sendConsultationRequest(case : ConsultationRequest,  onSuccess:()->Unit, onFailure:(String)->Unit )

}