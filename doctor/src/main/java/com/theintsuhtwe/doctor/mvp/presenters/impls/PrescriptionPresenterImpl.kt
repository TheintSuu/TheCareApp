package com.theintsuhtwe.doctor.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.mvp.views.PrescriptionView
import com.theintsuhtwe.doctor.utils.subTotalMedicinePrice
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import java.util.*

class PrescriptionPresenterImpl : PrescriptionPresenter, AbstractBasePresenter<PrescriptionView>(){
    var mTheCareModel = SpecialitiesModelImpl

    var mConsultationModel = ConsultationModelImpl

    var mPatientModel = PatientModelImpl

    var mTablet = "1"
    
    var routine : MutableList<String> = arrayListOf()
    var beforeAter : MutableList<String> = arrayListOf()


    var note : String = ""

    var mTotalMedicinesQuantity = "1"

    var mMedicineVO : MutableList<MedicineVO> = arrayListOf()

    override fun onUiReady(special: String, lifecycleOwner: LifecycleOwner) {
        mTheCareModel.getMedicinesBySpecailities(special, onSuccess = {
            mView?.displayPrescription(it)
        }, onFaiure = {

        })
    }

    override fun onTapFinishConsultation(id: String) {
        mConsultationModel.addPrescriptionByDoctor(id, mMedicineVO, onSuccess = {
            mView?.navigateToHome(id)
        }, onFailure = {

        })
    }

    override fun onTapSelectedRoutine(name: String) {
        routine.add(name)
    }

    override fun onTapRemoveRoutine(name: String) {
        routine.remove(name)
    }

    override fun onTapMedicineBeforeAfter(name: String) {
       beforeAter.add(name)
    }

    override fun onTapRemoveMedicineBeforeAfter(name: String) {
       beforeAter.remove(name)
    }

    override fun onTapSelectedRoutines(rou: String) {
      routine.add(rou)
    }

    override fun onSaveTablet(name: String) {
        mTablet = name
    }

    override fun onTapQuantity(quantity: String) {
       mTotalMedicinesQuantity = quantity
    }

    override fun onTapNote(name: String) {
       note = name
    }

    override fun onTapAdd(name : String, price : Long) {
        val subtotal = subTotalMedicinePrice(mTotalMedicinesQuantity.toInt() ,  price.toInt())
        val medicineVO = MedicineVO()
        medicineVO.id = UUID.randomUUID().toString()
        medicineVO.name = name
        medicineVO.note = note
        medicineVO.quantity = mTotalMedicinesQuantity.toLong()
        medicineVO.price = price
        medicineVO.tablet = mTablet
        medicineVO.sub_total = subtotal.toLong()
       routine?.forEach {

            medicineVO.routine = it.toString()+"/"

        }
        medicineVO.routine?.trimEnd('/')
        mMedicineVO.add(medicineVO)

        beforeAter.clear()
        routine.clear()






    }

    override fun onTapRemove(data: MedicineVO) {
       mMedicineVO.remove(data)
    }


    override fun onTapRequest(name: String) {
       
    }

    override fun onTapAccept(requesst: ConsultationRequest) {
       
    }

    override fun onTapCancel(id: ConsultationRequest) {

    }




    override fun onTapAddPrescription(id: String, price: Long) {
       mView?.showDetailPrescription(id, price)
    }


}