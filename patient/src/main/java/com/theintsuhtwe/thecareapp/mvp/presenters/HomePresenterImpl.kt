package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import com.theintsuhtwe.thecareapp.utils.SessionManager

class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>() {
    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl

    var mDoctorVO = DoctorVO()

    var mRecentDoctor : MutableList<DoctorVO> = arrayListOf()


    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

        getAllData(lifecycleOwner)

        mTheCareModel.getDeviceToken()
    }

    override fun onTapCategory(special: String) {

    }


    override fun onTapConfirm(id : String, special: String) {

        mView?.navigateToQuestion(id, special)

    }

    override fun onTapStartConsulataion(id: String, chatId: String) {
        isExistRecentDoctor(mDoctorVO)

        mConsultationModel.updateConsultationRequestByPateint(id, onSuccess = {
            mView?.navigateToChat(chatId)
        }, onFaiure = {})
    }




    private fun isExistRecentDoctor(doctorVO: DoctorVO){
        val isExistDoctor =  mRecentDoctor.find { doc ->
            doc.id.toString()
                .equals(
                    doctorVO.id.toString()
                )
        }
        when(isExistDoctor== null){
            true -> {
                mPatientModel.addRecentDoctor(SessionManager.patient_id.toString(),
                    mDoctorVO, onSuccess = {

                    }, onFailure = {

                    })
            }

        }
    }

    override fun onTapStartChat(id: String) {

      mView?.navigateToChat(id)
    }

    override fun onTapCancel() {

    }

    override fun onTapSpecialities(name: String) {
        mView?.showConfirmDialog(name)
    }

    private fun getAllData(lifecycleOwner: LifecycleOwner){
        mTheCareModel.getSpecailities(onSuccess ={

        }, onFaiure ={

        })

        mTheCareModel.getAllSpecialityFromDB().observe(
                lifecycleOwner,  Observer {
            mView?.displaySpecialities(it)

        })


        mPatientModel.getRecentDoctors(
                SessionManager.patient_id.toString(),
        onSuccess={

        }, onFaiure = {})

        mPatientModel.getAllRecentDoctorsFromDB()
                .observe(lifecycleOwner, Observer {
                    mRecentDoctor.clear()
                    mRecentDoctor.addAll(it)
            mView?.displayRecentDoctorList(it)
        }
        )

        mConsultationModel.getConsultationConfirmByPatient(
                SessionManager.request_id.toString(),
            onSuccess = {
                it.doctor?.let { it1 ->
                    mDoctorVO = it1
                    mView?.showConsultationRecevied(it) }
            },
            onFaiure = {

            }
        )
    }


    override fun onTapQuestion(descption : String, answer: String) {

    }

    override fun onTapRecentDoctor(id: String, special: String) {
       mView?.showRecentConfirmDialog(id, special)
    }


}