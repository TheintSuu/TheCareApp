package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.NoteView
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter


interface NotePresenter   : BasePresenter<NoteView> {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapSaveNote(id: String)

    fun onTypeNote(note : String)
}