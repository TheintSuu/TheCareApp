package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import com.theintsuhtwe.thecareapp.utils.getCurrentPatientInfo

class CaseSummaryPresenterImpl : CaseSummaryPresenter, AbstractBasePresenter<CaseSummaryView>() {

    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl

    var mPatient: Patient = Patient()


    override fun onUiReady(caseId : String, lifecycleOwner: LifecycleOwner) {


        mPatientModel.getQuestionByPatient(
                SessionManager.patient_id.toString(),
                onSuccess = {
                    mView?.displayGeneralQuestion(it)
                },
                onFaiure = {

                })


        mPatientModel.getPatientByEmail(SessionManager.patient_email.toString(),
        onSuccess = {
            mPatient = it
        }, onFailure = {

        }
        )

        mTheCareModel.getCaseSummaryToDB(caseId).
        observe(lifecycleOwner, Observer {

            it.questionList?.let { it1 -> mView?.displayCaseSummary(it1) }



        })


    }

    override fun onTapSendConsultationRequest(special: String, list: ArrayList<QuestionVO>, lifecycleOwner: LifecycleOwner) {


        mPatientModel.getQuestionByPatient(
                SessionManager.patient_id.toString(),
                onSuccess = {
                    val patient = mPatient
                    patient.question = it
                    mConsultationModel.sendConsultationRequest(SessionManager.patient_recent_doctor_id.toString(),patient, special, list, onSuccess = {
                        mView?.navigateToHome(it)
                    },
                            onFailure = {

                            }
                    )
                    SessionManager.patient_recent_doctor_id = ""
                },
                onFaiure = {

                }
        )




    }

    override fun onTapSpecialities(name: String) {
       
    }

    override fun onTapQuestion(descption: String, answer: String) {
       
    }

    override fun onTapRecentDoctor(id: String, doctorId: String) {

    }


}