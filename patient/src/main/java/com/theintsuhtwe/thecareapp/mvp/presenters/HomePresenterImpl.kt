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


    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        getAllData(lifecycleOwner)

        mTheCareModel.getDeviceToken()
    }

    override fun onTapCategory(special: String) {

    }


    override fun onTapConfirm(id : String, special: String) {
        mView?.navigateToQuestion(id, special)
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
            mView?.displayRecentDoctorList(it)
        }
        )

        mConsultationModel.getConsultationConfirmByPatient(
                SessionManager.request_id.toString(),
            onSuccess = {
                it.doctor?.let { it1 -> mView?.showConsultationRecevied(it) }
            },
            onFaiure = {

            }
        )
    }


    override fun onTapQuestion(descption : String, answer: String) {

    }


}