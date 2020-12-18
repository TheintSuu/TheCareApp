package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.*

interface ConsultationModel {

    fun getConsultationByPatient(id : String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit)

    fun updateConsultationRequestByPateint(id : String, onSuccess: () -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationRequestByDoctor( special : String, onSuccess: (List<ConsultationRequest>) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationRequestByDoctorId( id : String, onSuccess: (List<ConsultationRequest>) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationConfirmByPatient(id : String, onSuccess: (ConsultationRequest) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationByDoctor(id : String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationRequestById(id : String,  onSuccess: (con : ConsultationRequest) -> Unit, onFaiure: (String) -> Unit)

    fun getConsultationById(id : String, onSuccess: (ConsultationVO) -> Unit, onFaiure: (String) -> Unit)


    fun addConsultation(consulationId: String, dateTime: String, questionAnswerList: List<QuestionVO>, patientVO: Patient, doctorVO: DoctorVO,  onSuccess:(String)->Unit, onFailure:(String)->Unit)

    fun sendMessageBySenderType(id : String, message : MessageVO, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun getAllChatMessages(id : String,  onSuccess:(List<MessageVO>)->Unit, onFailure:(String)->Unit)

    fun addPrescriptionByDoctor(id : String, medicines : List<MedicineVO>, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun deletePrescriptionByDoctor(id : String, medicines : List<MedicineVO>, onSuccess:()->Unit, onFailure:(String)->Unit)

    fun createCaseSummary(patient: Patient, special: String,list : List<QuestionVO>,  onSuccess:(id : String)->Unit, onFailure:(String)->Unit )

    fun sendConsultationRequest(reId : String, patient :  Patient, special: String, caseSummaryVO: ArrayList<QuestionVO>, onSuccess: (id : String) -> Unit, onFailure: (String) -> Unit)


    fun getPrescriptionByConsultationId(id: String, onSuccess: (List<MedicineVO>) -> Unit, onFailure: (String) -> Unit)

    fun checkOut(address: String, medicines: List<MedicineVO>, total : String,  onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addConsultationNote(id: String, note : String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getNoteByConsultationId(
        id: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )
}