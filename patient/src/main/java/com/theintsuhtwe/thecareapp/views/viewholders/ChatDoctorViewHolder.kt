package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_chat_doctor.view.*

class ChatDoctorViewHolder (private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<MessageVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {

            }

        }
    }

    override fun bindData(data: MessageVO) {
        mData = data

        //itemView.tvPatientName.text = data.patient?.name
        data?.let{
            itemView.tvSendTime.text = data.sendTime

            itemView.tvMessage.text = data.text

            Glide.with(itemView)
                .load(data.senderImage)
                .optionalFitCenter()
                .into(itemView.ivDotorImage)
        }


    }

    override fun position(id: Long) {

    }
}