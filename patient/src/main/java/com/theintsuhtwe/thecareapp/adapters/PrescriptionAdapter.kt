package com.theintsuhtwe.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.delegates.MedicineItemDelegate
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.views.viewholders.PrescriptionViewHolder
import com.theintsuhtwe.thecareapp.views.viewholders.SpecialityViewHolder

class PrescriptionAdapter (private val mDelegate: MedicineItemDelegate) : BaseAdapter<BaseViewHolder<MedicineVO>, MedicineVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MedicineVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_prescription,parent,false)
        return  PrescriptionViewHolder(mDelegate,v)
    }

}