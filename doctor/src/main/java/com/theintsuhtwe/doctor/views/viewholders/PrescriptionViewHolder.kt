package com.theintsuhtwe.doctor.views.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.MedicineItemDelegate
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_general_answer.view.*
import kotlinx.android.synthetic.main.item_prescription.view.*


class PrescriptionViewHolder (private val mDelegate: MedicineItemDelegate, itemView: View) : BaseViewHolder<MedicineVO>(itemView) {

    var position : Long = 0
    init {


        itemView.tvAdd.setOnClickListener {
            mData?.let{

                it?.name?.let { it1 -> it?.price?.let { it2 ->
                    mDelegate.onTapAddPrescription(it1,
                        it2
                    )
                } }
            }
        }

    }

    override fun bindData(data: MedicineVO) {
        mData = data
        itemView.tvMedicineName.text = data.name

        if(data.isSelected == false)
        {
            itemView.tvAdd.setImageResource(R.drawable.ic_baseline_remove_circle_outline_24)
        }else
        {
            itemView.tvAdd.setImageResource(R.drawable.ic_baseline_add_circle_outline_24)
        }

        itemView.tvAdd.setOnClickListener {
            if(data.isSelected ==false) {

                data?.let {
                    itemView.tvAdd.setImageResource(R.drawable.ic_baseline_remove_circle_outline_24)
                    it.id?.let { it1 -> it.price?.let { it2 ->
                        mDelegate.onTapAddPrescription(it1,
                            it2
                        )
                    } }
                    data.isSelected= true
                }
            }
            else{
                itemView.tvAdd.setImageResource(R.drawable.ic_baseline_add_circle_outline_24)
                data.isSelected =false
                mDelegate.onTapRemove(data)
            }

        }



    }

    override fun position(id: Long)  {
        position = id
    }






}