package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.HomeView

class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {
    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl


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



    override fun onTapConfirm(id : String, category : String) {
        mView?.navigateToQuestion(id, category)
    }

    override fun onTapCancel() {

    }

    override fun onTapSpecialities(name: String) {
        mView?.showConfirmDialog(name)
    }

    private fun getAllData(lifecycleOwner: LifecycleOwner){
        mTheCareModel.getSpecailities(
            onSuccess = {
                mView?.displaySpecialities(it)
            },
            onFaiure = {

            }
        )

        mPatientModel.getRecentDoctors(
            "patient000",
        onSuccess = {
            mView?.displayRecentDoctorList(it)
        },
        onFaiure = {

        })
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