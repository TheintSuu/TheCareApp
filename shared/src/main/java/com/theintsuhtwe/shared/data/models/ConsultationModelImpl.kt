package com.theintsuhtwe.shared.data.models

import android.annotation.SuppressLint
import android.util.Log
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl
import com.theintsuhtwe.shared.utils.createNotiRequsetBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ConsultationModelImpl : ConsultationModel, BaseModel(){
    private val mFirebase  = CloudFirestoreFirebaseApiImpl
    override fun getConsultationByPatient(id: String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getConsultationByPatient(id, onSuccess, onFaiure)
    }

    override fun getConsultationByDoctor(id: String, onSuccess: (List<ConsultationVO>) -> Unit, onFaiure: (String) -> Unit) {
        return mFirebase.getConsultationByDoctor(id, onSuccess, onFaiure)
    }

    override fun addConsultation(consultation: ConsultationVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addConsultations(consultation, onSuccess, onFailure)
    }

    override fun sendMessageBySenderType(id: String, message: MessageVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.sendMessage(id, message, onSuccess, onFailure)
    }

    override fun addPrescriptionByDoctor(id: String, medicines: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.addPrescriptions(id, medicines, onSuccess, onFailure )
    }

    override fun deletePrescriptionByDoctor(id: String, medicines: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       mFirebase.deletePrescriptions(id, medicines, onSuccess, onFailure)
    }

    override fun createCaseSummary(case: CaseSummaryVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebase.createCaseSummary(case, onSuccess, onFailure)
    }

    @SuppressLint("CheckResult")
    override fun sendConsultationRequest(case: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebase.sendConsultationRequest(case, onSuccess, onFailure)

          mNotiApi.sendBroadCastRequestToDoctor(createNotiRequsetBody("to", "body", "title"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               Log.d("Broadcast Success", "Broadcast Success")
            }, {

            })
    }
}