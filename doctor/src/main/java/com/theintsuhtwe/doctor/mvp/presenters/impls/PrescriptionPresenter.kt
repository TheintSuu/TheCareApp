package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.delegates.MedicineItemDelegate
import com.theintsuhtwe.doctor.mvp.views.PrescriptionView
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter


interface PrescriptionPresenter : BasePresenter<PrescriptionView>, ConsultationItemDelegate, MedicineItemDelegate {

    fun onUiReady(special : String, lifecycleOwner: LifecycleOwner)

    fun onTapFinishConsultation(id : String)

    fun onTapSelectedRoutine(name : String)

    fun onTapRemoveRoutine(name : String)

    fun onTapMedicineBeforeAfter(name : String)

    fun onTapRemoveMedicineBeforeAfter(name : String)

    fun onTapSelectedRoutines(rou : String)

    fun onSaveTablet(name : String)

    fun onTapQuantity(quantity : String)

    fun onTapNote(name : String)

    fun onTapAdd(name : String, price : Long)


}