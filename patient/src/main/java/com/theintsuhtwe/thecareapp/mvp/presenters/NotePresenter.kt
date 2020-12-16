package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.NoteView

interface NotePresenter   : BasePresenter<NoteView> {
    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapSaveNote(id: String)

    fun onTypeNote(note : String)
}