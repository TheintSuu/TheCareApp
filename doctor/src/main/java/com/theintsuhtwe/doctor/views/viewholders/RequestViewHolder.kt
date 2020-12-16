package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.ConsultationRequest
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_request.view.*

class RequestViewHolder(private val mDelegate: ConsultationItemDelegate, itemView: View) : BaseViewHolder<ConsultationRequest>(itemView) {


    init {
        itemView.btnAccept.setOnClickListener {
            mData?.let {
                mDelegate.onTapAccept(it.id)
            }

        }

        itemView.setOnClickListener {
            mData?.let{
              //  mDelegate.onTapAccept(it.id)
                mDelegate.onTapRequest(it.id)
            }
        }
    }

    override fun bindData(data: ConsultationRequest) {
        mData = data

        itemView.tvPatientName.text = data.patient?.name

         data.patient?.question?.forEach {
            if(it.description == "မွေးနေ့"){
                itemView.tvBDDate.text= it.answer
                    }
        }

        Glide.with(itemView)
            .load(data.patient?.image)
            .optionalFitCenter()
            .into(itemView.ivConfirmDoctorImage)

    }

    override fun position(id: Long) {

    }
}