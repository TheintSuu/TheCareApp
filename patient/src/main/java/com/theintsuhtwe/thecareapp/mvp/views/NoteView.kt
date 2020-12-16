package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.views.BaseView

interface NoteView : BaseView {
    fun navigateToChat(id : String)

    fun displayPateintData(patient : Patient)
}