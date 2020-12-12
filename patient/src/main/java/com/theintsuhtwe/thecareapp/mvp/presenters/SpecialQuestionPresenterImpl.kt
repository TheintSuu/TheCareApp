package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView
import com.theintsuhtwe.thecareapp.mvp.views.SpecialQuestionView
import com.theintsuhtwe.thecareapp.utils.getCurrentPatientInfo

class SpecialQuestionPresenterImpl : SpecialQuestionPresenter, AbstractBasePresenter<SpecialQuestionView>(){

    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl



    override fun onUiReady(id : String, lifecycleOwner: LifecycleOwner) {
        mTheCareModel.getSpecialQuestionsBySpecailities(
                "Dental",
                onSuccess = {
                    mView?.displayQuestionBySpeciality(it)
                },
                onFaiure = {

                }
        )
    }

    override fun onTapContinue(list: List<QuestionVO>) {
        mConsultationModel.createCaseSummary(getCurrentPatientInfo( ), special = "Dental",
        list = list,
        onSuccess = {
            mView?.navigateToCaseSummary(it)
        },
                onFailure = {

                }
        )
    }



    override fun onTapSpecialities(name: String) {

    }

    override fun onTapQuestion(descption : String, answer: String) {
       mView?.addQuestionToCaseSummary(QuestionVO(
               description =  descption,
               answer = answer
       ))
    }

}