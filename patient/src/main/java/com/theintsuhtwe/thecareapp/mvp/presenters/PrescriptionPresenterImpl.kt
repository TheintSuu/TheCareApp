//package com.theintsuhtwe.thecareapp.mvp.presenters
//
//import androidx.lifecycle.LifecycleOwner
//import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
//import com.theintsuhtwe.shared.data.models.PatientModelImpl
//import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
//import com.theintsuhtwe.shared.data.vos.MedicineVO
//import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
//import com.theintsuhtwe.thecareapp.mvp.views.PrescriptionView
//import java.util.*
//
//class PrescriptionPresenterImpl : PrescriptionPresenter, AbstractBasePresenter<PrescriptionView>(){
//    var mTheCareModel = SpecialitiesModelImpl
//
//    var mConsultationModel = ConsultationModelImpl
//
//    var mPatientModel = PatientModelImpl
//
//    var mConsultationId = ""
//
//    var routine : MutableList<String> = arrayListOf()
//    var quantityMedicine : String = "1"
//    var beforeAter : String = ""
//    var note : String = ""
//
//    var mMedicineVO : MutableList<MedicineVO> = arrayListOf()
//
//    override fun onUiReady(special: String, lifecycleOwner: LifecycleOwner) {
//        mTheCareModel.getMedicinesBySpecailities(special, onSuccess = {
//            mView?.displayPrescription(it)
//        }, onFaiure = {
//
////        })
//    }
//
//    override fun onTapFinishConsultation(id: String) {
//        mConsultationModel.addPrescriptionByDoctor(id, mMedicineVO, onSuccess = {
//            mView?.navigateToChat(id)
//        }, onFailure = {
//
//        })
//    }
//
//    override fun onTapSelectedRoutine(name: String) {
//        routine.add(name)
//    }
//
//    override fun onTapRemoveRoutine(name: String) {
//        routine.remove(name)
//    }
//
//    override fun onTapMedicineBeforeAfter(name: String) {
//       beforeAter = name
//    }
//
//    override fun onTapRemoveMedicineBeforeAfter(name: String) {
//
//    }
//
//    override fun onTapSelectedRoutines(rou: String) {
//      routine.add(rou)
//    }
//
//    override fun onTapQuantity(quantity: String) {
//       quantityMedicine = quantity
//    }
//
//    override fun onTapNote(name: String) {
//       note = name
//    }
//
//    override fun onTapAdd(name : String, price : Long) {
//        val medicineVO = MedicineVO()
//        medicineVO.id = UUID.randomUUID().toString()
//        medicineVO.name = name
//        medicineVO.note = note
//        medicineVO.quantity = quantityMedicine.toLong()
//        medicineVO?.quantity?.let{
//
//            medicineVO.sub_total = it * routine.size.toLong()
//
//        }
//        medicineVO.price = price
//        mMedicineVO.add(medicineVO)
//
//
//
//
//
//    }
//
//    override fun onTapSpecialities(name: String) {
//
//    }
//
//    override fun onTapQuestion(descption: String, answer: String) {
//
//    }
//
//    override fun onTapRecentDoctor(id: String, doctorId: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onTapAddPrescription(id: String, price: Long) {
//       mView?.showDetailPrescription(id, price)
//    }
//
//
//}