package com.theintsuhtwe.thecareapp.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.DialogView
import com.theintsuhtwe.thecareapp.mvp.views.HomeView

interface HomePresenter : BasePresenter<HomeView>, SpecialitiesItemDelegate {
    fun onUiReady(lifecycleOwner: LifecycleOwner)

    fun onTapCategory(id : String)

    fun onTapConfirm(id : String, category : String)

    fun onTapStartChat(id : String)

    fun onTapCancel()
}