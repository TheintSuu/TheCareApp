package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface PrescriptionView : BaseView {

    fun displayPrescription(list : List<MedicineVO>)

    fun navigateToHome(id : String)

    fun showDetailPrescription(id : String, price : Long)





}