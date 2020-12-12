package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView
import com.theintsuhtwe.thecareapp.utils.SessionManager

class CaseSummaryPresenterImpl : CaseSummaryPresenter, AbstractBasePresenter<CaseSummaryView>() {

    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl


    override fun onUiReady(id : String, lifecycleOwner: LifecycleOwner) {
        mPatientModel.getQuestionByPatient(SessionManager.patient_id.toString(), onSuccess = {
            mView?.displayGeneralQuestion(it)
        },
                onFaiure = {

                }
        )
        mPatientModel.getCaseSummaryByPatient(id ,
                onSuccess = {
                    it.questionList?.let { it1 -> mView?.displaySpecialQuestion(it1) }
                },
                onFaiure = {

                }
        )


    }

    override fun onTapSendConsultationRequest(special: String, patient: Patient, list: List<QuestionVO>) {
        
    }

    override fun onTapSpecialities(name: String) {
       
    }

    override fun onTapQuestion(descption: String, answer: String) {
       
    }

  


}