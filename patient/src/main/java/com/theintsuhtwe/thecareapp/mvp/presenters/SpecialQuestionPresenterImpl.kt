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
import java.util.*
import kotlin.collections.ArrayList

class SpecialQuestionPresenterImpl : SpecialQuestionPresenter, AbstractBasePresenter<SpecialQuestionView>(){

    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl



    override fun onUiReady(special : String, lifecycleOwner: LifecycleOwner) {
        mTheCareModel.getSpecialQuestionsBySpecailities(
                special,
                onSuccess = {
                    mView?.displayQuestionBySpeciality(it)
                },
                onFaiure = {

                }
        )
    }

    override fun onTapContinue(list: ArrayList<QuestionVO>) {
        mView?.navigateToCaseSummary(mTheCareModel.addCaseSummaryToDB(list))
    }



    override fun onTapSpecialities(name: String) {

    }

    override fun onTapQuestion(descption : String, answer: String) {
       mView?.addQuestionToCaseSummary(
               QuestionVO(
               id = UUID.randomUUID().toString(),
               description =  descption,
               answer = answer
       )
       )
    }

}