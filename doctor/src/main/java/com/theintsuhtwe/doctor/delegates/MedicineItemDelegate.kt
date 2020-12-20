package com.theintsuhtwe.doctor.delegates

import com.theintsuhtwe.shared.data.vos.MedicineVO

interface MedicineItemDelegate {
    fun onTapAddPrescription(id : String, price : Long)

    fun onTapRemove(data : MedicineVO)
}