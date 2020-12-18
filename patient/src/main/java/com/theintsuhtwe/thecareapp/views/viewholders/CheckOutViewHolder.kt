package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_chat_doctor.view.*
import kotlinx.android.synthetic.main.item_medicine.view.*

class CheckOutViewHolder ( itemView: View) : BaseViewHolder<MedicineVO>(itemView) {


    init {
        itemView.setOnClickListener {
            mData?.let {

            }

        }
    }

    override fun bindData(data: MedicineVO) {
        mData = data

        //itemView.tvPatientName.text = data.patient?.name
        data?.let{
            itemView.tvQuantity.text = data.quantity.toString()

            itemView.tvSubTotal.text = data.sub_total.toString()

           itemView.tvMedicineName.text = data.name
        }


    }

    override fun position(id: Long) {

    }
}