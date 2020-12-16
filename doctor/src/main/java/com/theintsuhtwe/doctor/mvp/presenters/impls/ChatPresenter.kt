package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.mvp.views.ChatView
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface ChatPresenter : BasePresenter<ChatView>, ConsultationItemDelegate {

    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapPrescription(special: String)

    fun onTapSpecailQuestion(special : String)

    fun onTapNote()

    fun onTapMore()

    fun onTapSend(id : String, text : String, image : String)



}