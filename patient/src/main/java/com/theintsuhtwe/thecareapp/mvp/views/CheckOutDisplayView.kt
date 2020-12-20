package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface CheckOutDisplayView : BaseView {



    fun displayPrescriptionView(list : List<MedicineVO>)


    fun navigateToCheckOut(id : String)
}