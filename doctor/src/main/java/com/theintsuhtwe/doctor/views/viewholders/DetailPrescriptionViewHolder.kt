package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_medicine_info.view.*




class DetailPrescriptionViewHolder ( itemView: View) : BaseViewHolder<MedicineVO>(itemView) {

    var position : Long = 0

    override fun bindData(data: MedicineVO) {
        mData = data

        itemView.tvMedName.text = data.name

        itemView.tvAmount.text = "700 gm"

        itemView.tvQuantity.text = data.tablet


        itemView.tvDuration.text =  data.quantity.toString()+" Days"

        itemView.tvDayRoutine.text =  data.routine

        itemView.tvRepeat.text = "everyday"

        itemView.tvNote.text = data.note


    }

    override fun position(id: Long) {
        position = id
    }
}