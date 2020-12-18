package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.HistoryDelegate
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_chat_doctor.view.*
import kotlinx.android.synthetic.main.item_consultation.view.*

class HistoryViewHolder (private val mDelegate: HistoryDelegate, itemView: View) : BaseViewHolder<ConsultationVO>(itemView) {


    init {
        itemView.btnSendChat.setOnClickListener {
            mData?.let {
                mDelegate.onTapChat(it.id)
            }

        }

        itemView.btnPrescription.setOnClickListener {
            mData?.let {
                mDelegate.onTapPrescription(it.id)
            }

        }
    }

    override fun bindData(data: ConsultationVO) {
        mData = data

        //itemView.tvPatientName.text = data.patient?.name
        data?.let{
            itemView.tvDoctorName.text = data.doctor?.name

            itemView.tvDoctorSpeciality.text = data.doctor?.specialities

            itemView.tvConsultDate.text = data.careateAt

            Glide.with(itemView)
                    .load(data.doctor?.image)
                    .optionalFitCenter()
                    .into(itemView.ivConfirmDoctorImage)
        }


    }

    override fun position(id: Long) {

    }
}