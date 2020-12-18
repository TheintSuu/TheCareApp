package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CheckoutView
import com.theintsuhtwe.thecareapp.mvp.views.ConsultationHistoryView
import com.theintsuhtwe.thecareapp.utils.SessionManager

class HistoryPresenterImpl  :  HistoryPresenter, AbstractBasePresenter<ConsultationHistoryView>() {

    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl

    var mPatientModel = PatientModelImpl

    override fun onUiReady(id: String, lifecycleOwner: LifecycleOwner) {
        mModel.getConsultationByPatient(SessionManager.patient_id.toString(),
        onSuccess = {
            if(it.isEmpty()) mView?.displayEmptyView() else mView?.displayHistory(it)

        },
        onFaiure = {
            mView?.displayEmptyView()
        })
    }

    override fun onDialogUiReady(id: String) {

    }

    override fun onTapNote(id: String) {

    }

    override fun onTapPrescription(id: String) {
        mModel.getPrescriptionByConsultationId(id, onSuccess = {
            mView?.showPrescriptionDialog(id, it)
        }, onFailure = {

        })
    }

    override fun onTapChat(id: String) {
        mView?.navigateToChat(id)
    }

    override fun onTapTryAgain() {

    }
}