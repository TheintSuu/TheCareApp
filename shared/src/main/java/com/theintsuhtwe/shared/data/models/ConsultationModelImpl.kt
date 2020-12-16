package com.theintsuhtwe.shared.data.models

import android.annotation.SuppressLint
import android.media.session.MediaSessionManager
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object ConsultationModelImpl : ConsultationModel, BaseModel(){
    private val mFirebase  = CloudFirestoreFirebaseApiImpl
    override fun getConsultationByPatient(id: String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getConsultationByPatient(id, onSuccess, onFaiure)
    }

    override fun getConsultationRequestByDoctor(
        special: String,
        onSuccess: (List<ConsultationRequest>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
       mFirebase.getConsultationRequestByDoctor(special, onSuccess , onFaiure)
    }

    override fun getConsultationConfirmByPatient(
        id: String,
        onSuccess: (ConsultationRequest) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebase.getConfirmConsultation(id, onSuccess, onFaiure)
    }

    override fun getConsultationByDoctor(id: String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getConsultationByDoctor(id, onSuccess, onFaiure)
    }

    override fun getConsultationRequestById(id: String, doctorId : DoctorVO, onSuccess: (String) -> Unit, onFaiure: (String) -> Unit) {


        return mFirebase.getConsultationRequestById(id, onSuccess= {
            val con = it
           val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
           val currentDateAndTime: String = simpleDateFormat.format(Date())
           it.patient?.let { it1 ->
                   it.caseSummary?.toList()?.let { it3 ->
                           mFirebase.startConsultation(id,
                               currentDateAndTime,
                               it3,
                               it1,
                               doctorId,
                               onSuccess = {
                                   onSuccess(it)
                               },
                               onFailure = {
                                   onFaiure(it)
                               }


                           )

                   }

           }

       }, onFailure ={
           onFaiure(it)
       })
    }

    override fun getConsultationById(
        id: String,
        onSuccess: (ConsultationVO) -> Unit,
        onFaiure: (String) -> Unit
    ) {
       return mFirebase.getConsultationById(id, onSuccess, onFaiure)
    }

    override fun addConsultation(consulationId: String, dateTime: String, questionAnswerList: List<QuestionVO>, patientVO: Patient, doctorVO: DoctorVO,  onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
       mFirebase.startConsultation(consulationId, dateTime, questionAnswerList, patientVO, doctorVO,  onSuccess, onFailure)
    }

    override fun sendMessageBySenderType(id: String, message: MessageVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.sendMessage(id, message, onSuccess, onFailure)
    }

    override fun getAllChatMessages(
        id: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebase.getAllMessagesById(id, onSuccess, onFailure)
    }

    override fun addPrescriptionByDoctor(id: String, medicines: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addPrescriptions(id, medicines, onSuccess, onFailure )
    }

    override fun deletePrescriptionByDoctor(id: String, medicines: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.deletePrescriptions(id, medicines, onSuccess, onFailure)
    }

    override fun createCaseSummary(patient: Patient, special: String,list : List<QuestionVO>, onSuccess: (id : String) -> Unit, onFailure: (String) -> Unit) {
        mFirebase.createCaseSummary(patient, special, list,
        onSuccess ,
        onFailure)
    }

    @SuppressLint("CheckResult")
    override fun sendConsultationRequest(patient: Patient, special: String, id : ArrayList<QuestionVO>, onSuccess: (id : String) -> Unit, onFailure: (String) -> Unit) {
        mFirebase.sendConsultationRequest(patient, special,  id, onSuccess, onFailure)
    }

    override fun addConsultationNote(
        id: String,
        note: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebase.addConsultationNote(id, note, onSuccess, onFailure)
    }
}