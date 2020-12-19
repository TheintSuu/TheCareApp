package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView

class CaseSummaryQuestionPresenterImpl : CaseSummaryQuestionPresenter, AbstractBasePresenter<CaseSumaryQuestionView>(){
    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl

    override fun onUiReady(id : String, life: LifecycleOwner) {
        mPatientModel.getQuestionByPatient(id,
        onSuccess = {
            if(it.size>0) mView?.displayOneTimeAnswer(it)

        },
        onFaiure = {

        })
    }

    override fun onTapNext(id : String, type: String,  special : String, list : ArrayList<QuestionVO>) {

               when(type){
                   "once" -> {
                       mPatientModel.sendQuestion(id, list,
                               onSuccess = {
                                   //mTheCareModel.GeneralQuestionDeletAndInsertToDB(list)
                                   mView?.showAlwaysQuestion(id, id)

                               },
                               onFaiure = {

                               }
                       )
                   }
                       else -> {
                           mPatientModel.sendQuestion(id, list,
                                   onSuccess = {
                                       //  mTheCareModel.addGeneralQuestionToDB(list)
                                       mView?.navigaeToSpecialQuestion(id, special)
                                   },
                                   onFaiure = {

                                   }
                           )
                       }

               }


            }

    override fun onTapNextOnce(id: String, type: String, name: String, bdd: String, phone: String, special: String, list: ArrayList<QuestionVO>) {

        mPatientModel.getOnceTimeQuestion(id, name, bdd, phone, onSuccess = {
            mPatientModel.sendQuestion(id, list,
                    onSuccess = {
                        //mTheCareModel.GeneralQuestionDeletAndInsertToDB(list)

                        mView?.navigaeToSpecialQuestion(id, special)

                    },
                    onFaiure = {

                    }
            )
        }, onFailure = {

        })
    }

    override fun onTapSpecialities(name: String) {

    }

    override fun onTapQuestion(descption: String, answer: String) {
    }

    override fun onTapRecentDoctor(id: String, doctorId: String) {

    }
}