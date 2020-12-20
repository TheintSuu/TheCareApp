package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.MedicineItemDelegate
import com.theintsuhtwe.doctor.views.viewholders.PrescriptionViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder


class PrescriptionAdapter (private val mDelegate: MedicineItemDelegate) : BaseAdapter<BaseViewHolder<MedicineVO>, MedicineVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MedicineVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription,parent,false)
        return  PrescriptionViewHolder(mDelegate,v)
    }





}