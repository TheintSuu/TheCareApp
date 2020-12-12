package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.views.viewholders.RequestViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.viewholders.BaseViewHolder

class ConsultationRequestAdapter  (private val mDelegate: ConsultationItemDelegate) : BaseAdapter<BaseViewHolder<ConsultationRequest>, ConsultationRequest>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ConsultationRequest> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_request,parent,false)
        return  RequestViewHolder(mDelegate,v)
    }

}