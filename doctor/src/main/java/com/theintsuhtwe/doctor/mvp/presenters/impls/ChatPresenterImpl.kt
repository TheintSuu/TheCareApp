package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.ChatView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl :  ChatPresenter, AbstractBasePresenter<ChatView>() {
    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl

    var mPatientModel = PatientModelImpl

    var mConsulation = ConsultationVO()

    override fun onUiReady(id : String, lifecycleOwner: LifecycleOwner) {

      mModel.getConsultationById(id,
      onSuccess = {
          mConsulation = it
          getCaseSummary()
          it?.special?.let { it1 -> mView?.showSpeciality(it1) }
          mView?.displayPatientSpecialQuestion(it.caseSummary)
      },
          onFaiure = {

          }
      )




        mModel.getAllChatMessages(id, onSuccess = {
           mView?.displayPatientChat(it)
       },
       onFailure = {

       })

        mModel.getPrescriptionByConsultationId(id, onSuccess = {
            mView?.displayPrescription(it)
        }, onFailure = {

        })
    }

    private fun getCaseSummary(){
        mConsulation.patient?.id?.let {
            mPatientModel.getQuestionByPatient(
                it,
                onSuccess = {
                    mView?.displayPatientGeneralQuestion(it)
                },
                onFaiure = {

                })
        }
    }

    override fun onTapPrescription(special: String) {
            mView?.navigateToPrescription(special)
    }

    override fun onTapSpecailQuestion(special: String) {
        mView?.navigateToQuestion()
    }

    override fun onTapNote() {

    }

    override fun onTapMore() {

    }

    override fun onTapCheckOut(id: String) {

    }

    override fun onTapSend(id: String, text : String, image : String) {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateAndTime: String = simpleDateFormat.format(Date())

        val messageVO = MessageVO(
            id = UUID.randomUUID().toString(),
            text = text,
            image = image,
            senderId = SessionManager.doctor_id.toString(),
            senderImage = SessionManager.doctor_image.toString(),
            sendTime = currentDateAndTime
        )
      mModel.sendMessageBySenderType(id, messageVO, onSuccess = {

      }, onFailure = {

      })

    }

    override fun onTapRequest(name: String) {
       
    }

    override fun onTapAccept(requesst: ConsultationRequest) {
        
    }

    override fun onTapCancel(id: ConsultationRequest) {

    }






}