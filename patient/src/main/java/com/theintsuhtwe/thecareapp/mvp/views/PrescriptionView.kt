package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface PrescriptionView : BaseView {

    fun displayPrescription(list : List<MedicineVO>)

    fun navigateToChat(id : String)

    fun showDetailPrescription(id : String, price : Long)





}