package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.delegates.HistoryDelegate
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.doctor.views.viewpods.EmptyViewPod
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter

interface HomePresenter : BasePresenter<HomeView>, ConsultationItemDelegate, HistoryDelegate, EmptyViewPod.Delegate  {
    fun onUiReady(context : Context, lifecycleOwner: LifecycleOwner)
    fun onDialogUiReady(id : String)

    fun onTapSignOut()

//    fun onTapStartConsultation(id : String)

    fun onTapPostpone(time : String, consultation : ConsultationRequest)
}