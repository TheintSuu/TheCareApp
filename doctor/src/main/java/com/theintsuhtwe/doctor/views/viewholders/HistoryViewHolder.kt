package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_history.view.*


class HistoryViewHolder (private val mDelegate: ConsultationItemDelegate, itemView: View) : BaseViewHolder<ConsultationVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {
                mDelegate.onTapRequest(it.id)
            }

        }
    }

    override fun bindData(data: ConsultationVO) {
        mData = data

        itemView.tvName.text = data.patient?.name

        itemView.tvBDDate.text= data.patient?.email

        Glide.with(itemView)
            .load(data.patient?.image)
            .optionalFitCenter()
            .into(itemView.ivConfirmDoctorImage)

        //data.image.let { itemView.ivSpecialityImage.loadImage(it.toUri())}
    }
}