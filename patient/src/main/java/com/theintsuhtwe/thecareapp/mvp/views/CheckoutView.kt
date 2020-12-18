package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface CheckoutView  : BaseView{
    fun displayPrescription(list : List<MedicineVO>)



    fun navigateToHome()
}