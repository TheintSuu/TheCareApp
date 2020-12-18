package com.theintsuhtwe.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.views.viewholders.DetailPrescriptionViewHolder

class DetailPrescriptionAdapter () : BaseAdapter<BaseViewHolder<MedicineVO>, MedicineVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MedicineVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_medicine_info,parent,false)
        return  DetailPrescriptionViewHolder(v)
    }

}