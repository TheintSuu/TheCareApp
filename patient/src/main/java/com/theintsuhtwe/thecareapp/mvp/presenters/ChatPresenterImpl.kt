package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.ChatView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl :  ChatPresenter, AbstractBasePresenter<ChatView>() {
    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl

    var mPatientModel = PatientModelImpl

    override fun onUiReady(id : String, lifecycleOwner: LifecycleOwner) {

      mModel.getConsultationById(id,
      onSuccess = {

          it?.special?.let { it1 -> mView?.showSpeciality(it1) }
          mView?.displayPatientSpecialQuestion(it.caseSummary)
      },
          onFaiure = {

          }
      )

        mPatientModel.getQuestionByPatient(
            SessionManager.patient_id.toString(),
            onSuccess = {
                mView?.displayPatientGeneralQuestion(it)
            },
            onFaiure = {

            })


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

    override fun onTapPrescription(special: String) {
            mView?.navigateToPrescription(special)
    }

    override fun onTapSpecailQuestion(special: String) {

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
            senderId = SessionManager.patient_id.toString(),
            senderImage = SessionManager.patient_photo.toString(),
            sendTime = currentDateAndTime
        )
      mModel.sendMessageBySenderType(id, messageVO, onSuccess = {

      }, onFailure = {

      })

    }

    override fun onTapSpecialities(name: String) {

    }

    override fun onTapQuestion(descption: String, answer: String) {

    }

    override fun onTapRecentDoctor(id: String, doctorId: String) {
        TODO("Not yet implemented")
    }


}