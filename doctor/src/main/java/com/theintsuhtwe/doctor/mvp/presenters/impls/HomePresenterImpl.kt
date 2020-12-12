package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>(){
    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl
    override fun onUiReady(id : String,lifecycleOwner: LifecycleOwner) {
        getAllData(id, lifecycleOwner)

        mTheCareModel.getDeviceToken()


    }

    override fun onTapStartConsultation() {

    }

    override fun onTapRequest(name: String) {
        mView?.showDialog()
    }

    override fun onTapConsultationHistory(name: String) {

    }


    private fun getAllData(id: String, lifecycleOwner: LifecycleOwner){
//        mTheCareModel.getGeneralQuestionsBySpecailities(
//            id,
//            onSuccess = {
//                mView?.displayGeneralQuestionByCateogry(it)
//            },
//            onFaiure = {
//
//            }
//        )
//
        mModel.getConsultationRequestByDoctor(
            "Dental",
            onSuccess = {
                mView?.displayConsultationRequest(it)
            },
            onFaiure = {

            }
        )

        mModel.getConsultationByDoctor(
            "doc000",
            onSuccess = {
                mView?.displayConsultationHistory(it)
            },
            onFaiure = {

            }
        )

        mModel.getConsultationConfirmByPatient(
                "request000",
                onSuccess = {

                },
                onFaiure = {

                }
        )




//        mTheCareModel.getMedicinesBySpecailities(
//            id,
//            onSuccess = {
//                mView?.displayMedicineQuestionByCateogry(it)
//            },
//            onFaiure = {
//
//            }
//        )
    }

}