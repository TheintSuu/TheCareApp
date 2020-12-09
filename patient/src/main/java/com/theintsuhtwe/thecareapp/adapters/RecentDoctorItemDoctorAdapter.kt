package com.theintsuhtwe.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.views.viewholders.RecentDoctorViewHolder

class RecentDoctorItemDoctorAdapter (private val mDelegate: SpecialitiesItemDelegate) : BaseAdapter<BaseViewHolder<DoctorVO>, DoctorVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DoctorVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_doctor,parent,false)
        return  RecentDoctorViewHolder(mDelegate,v)
    }

}