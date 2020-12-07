package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {
    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl


    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        getAllData(lifecycleOwner)

        mTheCareModel.getDeviceToken()
    }

    override fun onTapCategory(id: String) {
      mTheCareModel.getDoctorBySpecialities(
          id,
          onSuccess = {

          },
          onFaiure = {

          }

      )
    }

    override fun onTapConsultationRequest(case: CaseSummaryVO, patient: Patient, specail: CategoryVO) {
        mConsultationModel.sendConsultationRequest(ConsultationRequest(),
        onSuccess = {

        },
            onFailure = {

            }
        )
    }

    override fun onTapStartConsultation(case: CaseSummaryVO, patient: Patient, doctorVO: DoctorVO, specail: CategoryVO) {

    }

    override fun onTapNextQuestion(id: String) {

    }

    override fun onTapSpecialities(name: String) {

    }

    private fun getAllData(lifecycleOwner: LifecycleOwner){
        mTheCareModel.getSpecailities(
            onSuccess = {
                mView?.displaySpecialities(it)
            },
            onFaiure = {

            }
        )
    }


    private fun sendBroadCastRequest(){
        mConsultationModel.sendConsultationRequest(
                ConsultationRequest(),
                onSuccess = {

                },
                onFailure = {

                }
        )
    }

    private fun sendDirectRequsest(lifecycleOwner: LifecycleOwner){

    }
}