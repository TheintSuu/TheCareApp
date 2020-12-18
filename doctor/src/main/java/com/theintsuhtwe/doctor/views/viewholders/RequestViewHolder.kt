package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.utils.randomBDate
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_request.view.*

class RequestViewHolder(private val mDelegate: ConsultationItemDelegate, itemView: View) : BaseViewHolder<ConsultationRequest>(itemView) {

    var position : Long = 0
    init {
        itemView.btnAccept.setOnClickListener {
            mData?.let {
                mDelegate.onTapAccept(it.id)
            }

        }

        itemView.btnSkip.setOnClickListener {
            mData?.let {
                mDelegate.onTapRequest(it.id)
            }

        }

        itemView.btnSkip.setOnClickListener {
            mData?.let{
              //  mDelegate.onTapAccept(it.id)
                mDelegate.onTapCancel(it)
            }
        }
    }

    override fun bindData(data: ConsultationRequest) {

        mData = data

        itemView.tvPatientName.text = data.patient?.name

        itemView.tvBDDate.text = "BirthDay " + randomBDate()


        if (data.recent_id!=SessionManager.doctor_id) itemView.tvConsultType.text = "လူနာအသစ်" else itemView.tvConsultType.text = "ဆွေးနွေးခဲ့ဖူးသော လူနာ"
        if (data.recent_id!=SessionManager.doctor_id) itemView.btnSkip.visibility =  View.GONE else itemView.btnSkip.visibility = View.VISIBLE

        Glide.with(itemView)
            .load(data.patient?.image)
            .into(itemView.ivConfirmDoctorImage)

    }

    override fun position(id: Long) {
        position = id
    }
}