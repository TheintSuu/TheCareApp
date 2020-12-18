package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.models.ConsultationModelImpl
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.PatientModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter
import com.theintsuhtwe.thecareapp.mvp.views.CheckoutView
import com.theintsuhtwe.thecareapp.utils.SessionManager

class CheckoutPresenterImpl  :  CheckoutPresenter, AbstractBasePresenter<CheckoutView>()  {

    var mTheCareModel = SpecialitiesModelImpl

    var mModel = ConsultationModelImpl

    var mDoctorModel = DoctorModelImpl

    var mPatientModel = PatientModelImpl

    var total : Long = 0

    var mMedicines : List<MedicineVO> = arrayListOf()
    override fun onUiReady(id: String, lifecycleOwner: LifecycleOwner) {
      mModel.getPrescriptionByConsultationId(id, onSuccess = {
          medicines ->
          medicines.forEach {
              it.sub_total?.let {
                  subTotaol ->
                  total += subTotaol
              }
          }
          mMedicines =  medicines

          mView?.displayPrescription( medicines)
      }, onFailure = {

      })
    }

    override fun onTapCheckOut(id: String) {
        SessionManager.patient_address = "No.18 thamadi 4th street Thingayankyn Yangon"
       mModel.checkOut(SessionManager.patient_address.toString(), mMedicines, total.toString(), onSuccess = {
           mView?.navigateToHome()
       },
           onFailure = {

           }
       )
    }


}