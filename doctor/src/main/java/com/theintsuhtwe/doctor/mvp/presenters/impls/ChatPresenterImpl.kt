package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.ChatView
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

class ChatPresenterImpl :  ChatPresenter, AbstractBasePresenter<ChatView>() {
    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl

    override fun onUiReady(id : String, lifecycleOwner: LifecycleOwner) {

      mModel.getConsultationById(id,
      onSuccess = {
          it.patient?.question?.let { it1 -> mView?.displayPatientGeneralQuestion(it1) }

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
    }

    override fun onTapPrescription(special: String) {

    }

    override fun onTapSpecailQuestion(special: String) {

    }

    override fun onTapNote() {

    }

    override fun onTapMore() {

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

    override fun onTapAccept(id: String) {

    }

    override fun onTapConsultationHistory(name: String) {

    }
}