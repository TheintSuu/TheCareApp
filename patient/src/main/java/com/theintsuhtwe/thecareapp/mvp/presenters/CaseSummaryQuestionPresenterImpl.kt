package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView
import com.theintsuhtwe.thecareapp.mvp.views.HomeView

class CaseSummaryQuestionPresenterImpl : CaseSummaryQuestionPresenter, AbstractBasePresenter<CaseSumaryQuestionView>(){
    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl

    override fun onUiReady(id : String, life: LifecycleOwner) {
        mPatientModel.getQuestionByPatient(id,
        onSuccess = {
            mView?.displayOneTimeAnswer(it)
        },
        onFaiure = {

        })
    }

    override fun onTapNext(id : String, type: String,  special : String, list : List<QuestionVO>) {
        when(type){
            "once" -> {
                mPatientModel.sendQuestion(id, list,
                    onSuccess = {
                        mView?.showAlwaysQuestion(id, id)
                    },
                    onFaiure = {

                    }
                )
            }
            else -> {

//                mConsultationModel.sendConsultationRequest(
//
//                )
                mPatientModel.sendQuestion(id, list,
                    onSuccess = {
                        mView?.navigaeToSpecialQuestion(id, id)
                    },
                    onFaiure = {

                    }
                )
            }
        }



    }

    override fun onTapSpecialities(name: String) {

    }

    override fun onTapQuestion(descption: String, answer: String) {
    }
}