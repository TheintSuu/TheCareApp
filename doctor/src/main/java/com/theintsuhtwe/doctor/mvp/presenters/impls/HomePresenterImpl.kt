package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>(){
    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl
    override fun onUiReady(id : String,lifecycleOwner: LifecycleOwner) {
        getAllData(id, lifecycleOwner)

        mTheCareModel.getDeviceToken()


    }

    override fun onTapStartConsultation(consultation: ConsultationRequest) {

    }

    override fun onTapPostpone(time: String, consultation: ConsultationRequest) {

    }



    override fun onTapRequest(name: String) {
        mDoctorModel.getDoctorByEmail(SessionManager.doctor_email.toString(), onSuccess = {
            mModel.getConsultationRequestById(name,
                doctorId = it,
                onSuccess = {
                    mView?.navigateToChatActivity(name)
                },
                onFaiure = {

                })
        }, onFailure = {

        })

        //mView?.showDialog()
    }

    override fun onTapAccept(id: String) {

//        mModel.getConsultationRequestById(id,
//        onSuccess = {
//            mView?.navigateToChatActivity(id)
//        },
//        onFaiure = {
//
//        })

    }

    override fun onTapConsultationHistory(name: String) {

    }


    private fun getAllData(id: String, lifecycleOwner: LifecycleOwner){


        mModel.getConsultationRequestByDoctor(
           SessionManager.doctor_speciality.toString(),
            onSuccess = {
                mView?.displayConsultationRequest(it)
            },
            onFaiure = {

            }
        )

        mModel.getConsultationByDoctor(
            SessionManager.doctor_id.toString(),
            onSuccess = {
                mView?.displayConsultationHistory(it)
            },
            onFaiure = {

            }
        )





    }

}