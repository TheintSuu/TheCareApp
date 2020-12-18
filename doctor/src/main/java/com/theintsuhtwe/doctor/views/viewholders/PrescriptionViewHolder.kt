package com.theintsuhtwe.doctor.views.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
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



    }

    override fun position(id: Long)  {
        position = id
    }






}