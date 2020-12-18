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

        //itemView.tvPatientName.text = data.patient?.name
        when(position.toInt()%2==0){
            true -> {
                data?.let{

                    itemView.tvMedName.text = data.name

                    itemView.tvAmount.text = data.total_day
//            data.routine?.time?.forEach {
//                itemView.tvMedName.text = (itemView.tvMedName.text as String?)?.plus(it+"/")
//            }
//
                    itemView.tvDuration.text = "Morning/Noon/Evening"
                    itemView.tvDayRoutine.text = "10 Days"
                    itemView.tvRepeat.text = "everyday"
                    itemView.tvNote.text = data.note
                }
            }
            else -> {
                data?.let{

                    itemView.tvMedName.text = data.name

                    itemView.tvAmount.text = data.total_day
//            data.routine?.time?.forEach {
//                itemView.tvMedName.text = (itemView.tvMedName.text as String?)?.plus(it+"/")
//            }
//
                    itemView.tvDayRoutine.text = "Morning"
                    itemView.tvDuration.text = "3 weels"
                    itemView.tvRepeat.text = "not everyday"
                    itemView.tvNote.text = data.note
                }
            }
        }



    }

    override fun position(id: Long) {
        position = id
    }
}