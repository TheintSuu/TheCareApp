package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_recent_doctor.view.*


class RecentDoctorViewHolder(private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<DoctorVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {
               mDelegate.onTapRecentDoctor(it.id, it.specialities)
            }

        }


    }

    override fun bindData(data: DoctorVO) {
        mData = data

        itemView.tvRecentDoctorName.text = data.name

        itemView.tvSpecialityName.text = data.specialities


        Glide.with(itemView)
                .load(data.image)
                .centerCrop()
                .into(itemView.ivrecentDoctorImage)

//        data.image.let { itemView.ivrecentDoctorImage.loadImage(it.toUri())}
    }

    override fun position(id: Long) {

    }
}