package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_chat_bypatient.view.*


class ChatPatientViewHolder (private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<MessageVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {

            }

        }
    }

    override fun bindData(data: MessageVO) {
        mData = data


       itemView.patientMessage.text = data.text

        itemView.patientSendTime.text = data.sendTime

        Glide.with(itemView)
            .load(data.senderImage)
            .optionalFitCenter()
            .into(itemView.patientImage)

    }

    override fun position(id: Long) {

    }
}