package com.theintsuhtwe.doctor.views.viewholders

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.delegates.HistoryDelegate
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_history.view.ivConfirmDoctorImage
import kotlinx.android.synthetic.main.item_history.view.tvBDDate
import kotlinx.android.synthetic.main.item_request.view.*


class HistoryViewHolder (private val mDelegate: HistoryDelegate, itemView: View) : BaseViewHolder<ConsultationVO>(itemView) {


    init {
        itemView.btnSend.setOnClickListener {
            mData?.let {
                mDelegate.onTapChat(it.id, it.statue)
            }

        }

        itemView.btnPrescription.setOnClickListener {
            mData?.let {
                mDelegate.onTapPrescription(it.id)
            }

        }

       itemView.btnSendChat.setOnClickListener {
           mData?.let {
               mDelegate.onTapNote(it.id)
           }
       }
    }

    override fun bindData(data: ConsultationVO) {
        mData = data

        when(data.statue){
            "finish" -> {
                itemView.btnSend.setTextColor(Color.BLACK)
               itemView.btnSend.setBackgroundResource(R.drawable.btn_serach)
            }else -> {
                itemView.btnSend.isClickable = true
            }
        }


        itemView.tvName.text = data.patient?.name


       // itemView.tvBDDate.text=  data.patient?.bDate



        Glide.with(itemView)
            .load(data.patient?.image)
            .centerCrop()
            .into(itemView.ivConfirmDoctorImage)

        //data.image.let { itemView.ivSpecialityImage.loadImage(it.toUri())}
    }

    override fun position(id: Long) {

    }
}