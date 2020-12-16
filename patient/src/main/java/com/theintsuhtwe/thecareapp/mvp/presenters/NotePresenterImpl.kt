package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

import com.theintsuhtwe.thecareapp.mvp.views.NoteView
import com.theintsuhtwe.thecareapp.mvp.views.PrescriptionView
import com.theintsuhtwe.thecareapp.utils.SessionManager

class NotePresenterImpl: NotePresenter, AbstractBasePresenter<NoteView>(){
    private var mConsultation = ConsultationModelImpl

    private var mPatientVO = Patient()

    private var mPatient = PatientModelImpl

    private var mNote : String ?= null


    override fun onUiReady(id: String, lifecycleOwner: LifecycleOwner) {
        mPatient.getPatientByEmail(SessionManager.patient_email.toString(),
        onSuccess = {
            mPatientVO = it
            mView?.displayPateintData(it)
        },
        onFailure = {

        })
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