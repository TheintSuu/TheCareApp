package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.doctor.utils.SIGN_OUT
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.doctor.utils.prepareNotificationForDoctor
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.shared.utils.currentDate
import java.text.SimpleDateFormat
import java.util.*

class HomePresenterImpl : HomePresenter, AbstractBasePresenter<HomeView>(){

    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl

    lateinit var mContext : Context

    private var doctor  = DoctorVO()

    private var flag = false

    private var consulation = ConsultationRequest()

    private var dialogMedicineVO : List<MedicineVO> = arrayListOf()

    private var mConsulationRequest : MutableList<ConsultationRequest> = arrayListOf()

    override fun onUiReady(context : Context, lifecycleOwner: LifecycleOwner) {
        mContext = context

        getAllData( lifecycleOwner)

        mTheCareModel.getDeviceToken()

        mDoctorModel.getDoctorByEmail(SessionManager.doctor_email.toString(), onSuccess = { doc ->
            doctor = doc
        }, onFailure = {

        })




    }

    override fun onDialogUiReady(id: String) {
        mModel.getPrescriptionByConsultationId(id, onSuccess = {

        }, onFailure = {

        }
        )


    }

    override fun onTapSignOut() {
        SessionManager.doctor_login_status = SIGN_OUT
        mView?.navigateToLogin()
    }


    override fun onTapPostpone(time: String, consultation: ConsultationRequest) {

    }



    override fun onTapRequest(name: String) {


        mView?.showDialog()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTapAccept(requesst: ConsultationRequest) {

       consulation = requesst



        var notiObj=  prepareNotificationForDoctor(mContext, consulation.patient?.device_token, SessionManager.doctor_name.toString(),SessionManager.doctor_id.toString())
        mDoctorModel.sendNotificationToPatient(notiObj,onSuccess = {
            Log.d("onsuccess", it.success.toString())
        }, onFailure = {
            Log.d("notionFailure", it)
        })

        onTapChatByUiReady(consulation.id)


    }

    override fun onTapCancel(request : ConsultationRequest) {
        mConsulationRequest.remove(request)
        mView?.removeConsultationRequest(request)
       // mView?.displayConsultationRequest(mConsulationRequest)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onTapChatByUiReady(id : String){
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateAndTime: String = simpleDateFormat.format(Date())

        consulation.caseSummary?.let {
            consulation.patient?.let { it1 ->
                mModel.addConsultation(id,
                    currentDate().toString(),
                    it,
                    it1,
                    doctor,
                    onSuccess = { conId ->
                        mView?.navigateToChatActivity(conId)
                    },
                    onFailure = {

                    }


                )
            }
        }
    }



    override fun onTapNote(id: String) {
        mModel.getNoteByConsultationId(id, onSuccess = {
            if(it.isNotEmpty())   mView?.showDialogNote(it)
        }, onFailure = {

        }
        )

    }

    override fun onTapPrescription(id: String) {
        mModel.getPrescriptionByConsultationId(id, onSuccess = {
            if(it.isNotEmpty())  mView?.displayPrescriptionDialog(id, it)

        }, onFailure = {

        }
        )

    }

    override fun onTapChat(id: String, type: String) {
       mView?.navigateToChatHistoryActivity(id, type)
    }


    override fun onTapTryAgain() {

    }


    private fun getAllData( lifecycleOwner: LifecycleOwner){



        mModel.getConsultationRequestByDoctor(

            SessionManager.doctor_speciality.toString(),
            onSuccess = {
                    list->

                    filterRecentDoctor(list)



            },
            onFaiure = {

            }
        )






        mModel.getConsultationByDoctor(
            SessionManager.doctor_id.toString(),
            onSuccess = {list->

                if(list.isEmpty()) mView?.displayEmptyView() else mView?.displayConsultationHistory(list)


            },
            onFaiure = {

            }
        )


    }

    private fun filterRecentDoctor(list : List<ConsultationRequest>){
        val isExistDoctor =  list.find { doc ->

           doc.recent_id.equals(SessionManager.doctor_id)
        }

        mView?.displayConsultationRequest(list)

    }



}