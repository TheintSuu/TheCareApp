package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.ChatView

interface DetailPrescriptionPresenter: BasePresenter<ChatView> {

    fun onUiReady(id : String, lifecycleOwner: LifecycleOwner)

    fun onTapAddPrescription(medicine : MedicineVO)
}