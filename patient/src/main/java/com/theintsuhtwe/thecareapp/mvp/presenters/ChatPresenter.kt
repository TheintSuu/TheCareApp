package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.ChatView

interface ChatPresenter : BasePresenter<ChatView>,SpecialitiesItemDelegate {

    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapPrescription(special: String)

    fun onTapSpecailQuestion(special : String)

    fun onTapNote()

    fun onTapMore()


    fun onTapCheckOut(id : String)

    fun onTapSend(id : String, text : String, image : String)



}