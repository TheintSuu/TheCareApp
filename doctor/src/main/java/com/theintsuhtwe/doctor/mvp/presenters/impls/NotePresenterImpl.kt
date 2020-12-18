package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.NoteView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class NotePresenterImpl: NotePresenter, AbstractBasePresenter<NoteView>(){
    private var mConsultation = ConsultationModelImpl

    private var mPatientVO = Patient()

    private var mPatient = PatientModelImpl

    private var mNote : String ?= null


    override fun onUiReady(id: String, lifecycleOwner: LifecycleOwner) {


        mConsultation.getConsultationById(id, onSuccess = {

            it.patient?.let { it1 -> mView?.displayPateintData(it1) }
        }, onFaiure = {

        }
        )
    }

    override fun onTapSaveNote(id: String) {
        mNote?.let {
            mConsultation.addConsultationNote(id, it, onSuccess = {
            mView?.navigateToChat(id)
        },
            onFailure = {

            })

        }
    }

    override fun onTypeNote(note: String) {
       mNote = note
    }
}