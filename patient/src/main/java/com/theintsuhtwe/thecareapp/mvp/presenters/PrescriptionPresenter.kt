//package com.theintsuhtwe.thecareapp.mvp.presenters
//
//import androidx.lifecycle.LifecycleOwner
//import com.theintsuhtwe.shared.data.vos.MedicineVO
//import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
//import com.theintsuhtwe.thecareapp.delegates.MedicineItemDelegate
//import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
//import com.theintsuhtwe.thecareapp.mvp.views.PrescriptionView
//
//interface PrescriptionPresenter : BasePresenter<PrescriptionView>, SpecialitiesItemDelegate,    MedicineItemDelegate {
//
//    fun onUiReady(special : String, lifecycleOwner: LifecycleOwner)
//
//    fun onTapFinishConsultation(id : String)
//
//    fun onTapSelectedRoutine(name : String)
//
//    fun onTapRemoveRoutine(name : String)
//
//    fun onTapMedicineBeforeAfter(name : String)
//
//    fun onTapRemoveMedicineBeforeAfter(name : String)
//
//    fun onTapSelectedRoutines(rou : String)
//
//    fun onTapQuantity(quantity : String)
//
//    fun onTapNote(name : String)
//
//    fun onTapAdd(name : String, price : Long)
//}