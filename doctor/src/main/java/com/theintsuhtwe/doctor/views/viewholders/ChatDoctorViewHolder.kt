package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_chat_doctor.view.*
import kotlinx.android.synthetic.main.item_request.view.*

class ChatDoctorViewHolder (private val mDelegate: ConsultationItemDelegate, itemView: View) : BaseViewHolder<MessageVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {

            }

        }
    }

    override fun bindData(data: MessageVO) {
        mData = data

        //itemView.tvPatientName.text = data.patient?.name

        itemView.tvSendTime.text = data.sendTime

        itemView.tvMessage.text = data.text

        Glide.with(itemView)
            .load(data.senderImage)
            .optionalFitCenter()
            .into(itemView.ivDotorImage)

    }

    override fun position(id: Long) {

    }
}