package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.delegates.HistoryDelegate
import com.theintsuhtwe.doctor.views.viewholders.HistoryViewHolder
import com.theintsuhtwe.doctor.views.viewholders.RequestViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder

class ConsultationHistoryAdapter (private val mDelegate: HistoryDelegate) : BaseAdapter<BaseViewHolder<ConsultationVO>, ConsultationVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ConsultationVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
        return  HistoryViewHolder(mDelegate,v)
    }

}