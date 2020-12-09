package com.theintsuhtwe.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.views.viewholders.SpecialityViewHolder

class SpecialityItemAdapter (private val mDelegate: SpecialitiesItemDelegate) : BaseAdapter<BaseViewHolder<CategoryVO>, CategoryVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CategoryVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality,parent,false)
        return  SpecialityViewHolder(mDelegate,v)
    }

}