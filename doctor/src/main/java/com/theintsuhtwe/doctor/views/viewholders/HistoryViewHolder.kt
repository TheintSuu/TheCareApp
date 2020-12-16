package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_history.view.ivConfirmDoctorImage
import kotlinx.android.synthetic.main.item_history.view.tvBDDate
import kotlinx.android.synthetic.main.item_request.view.*


class HistoryViewHolder (private val mDelegate: ConsultationItemDelegate, itemView: View) : BaseViewHolder<ConsultationVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {

            }

        }
    }

    override fun bindData(data: ConsultationVO) {
        mData = data

        itemView.tvName.text = data.patient?.name

        data.patient?.question?.forEach {
            if(it.description == "မွေးနေ့"){
                itemView.tvBDDate.text= it.answer
            }
        }

        Glide.with(itemView)
            .load(data.patient?.image)
            .centerCrop()
            .into(itemView.ivConfirmDoctorImage)

        //data.image.let { itemView.ivSpecialityImage.loadImage(it.toUri())}
    }

    override fun position(id: Long) {

    }
}