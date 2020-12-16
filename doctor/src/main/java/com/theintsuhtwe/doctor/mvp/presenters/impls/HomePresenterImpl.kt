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


        //mView?.showDialog()
    }

    override fun onTapAccept(id: String) {

        mDoctorModel.getDoctorByEmail(SessionManager.doctor_email.toString(), onSuccess = {
            mModel.getConsultationRequestById(id,
                    doctorId = it,
                    onSuccess = {
                        mView?.navigateToChatActivity(id)
                    },
                    onFaiure = {

                    })
        }, onFailure = {

        })

    }

    override fun onTapConsultationHistory(name: String) {

    }


    private fun getAllData(id: String, lifecycleOwner: LifecycleOwner){


        mModel.getConsultationRequestByDoctor(
           SessionManager.doctor_speciality.toString(),
            onSuccess = {list->
                list.isNotEmpty()?.let{
                    mView?.displayConsultationRequest(list)

            }


            },
            onFaiure = {

            }
        )

        mModel.getConsultationByDoctor(
            SessionManager.doctor_id.toString(),
            onSuccess = {list->
                when(list.size){
                    0 -> {

                    }
                    else -> {
                    mView?.displayConsultationHistory(list)
                }
                }

            },
            onFaiure = {

            }
        )





    }

}