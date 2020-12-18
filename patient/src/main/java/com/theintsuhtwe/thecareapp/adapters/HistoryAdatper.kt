package com.theintsuhtwe.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.delegates.HistoryDelegate
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.views.viewholders.HistoryViewHolder
import com.theintsuhtwe.thecareapp.views.viewholders.SpecialityViewHolder

class HistoryAdatper(private val mDelegate: HistoryDelegate) : BaseAdapter<BaseViewHolder<ConsultationVO>, ConsultationVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ConsultationVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_consultation,parent,false)
        return HistoryViewHolder(mDelegate,v)
    }

}