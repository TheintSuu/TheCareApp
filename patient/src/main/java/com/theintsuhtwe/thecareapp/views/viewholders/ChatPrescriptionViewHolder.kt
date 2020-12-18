package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.MedicineItemDelegate
import kotlinx.android.synthetic.main.item_chat_prescription.view.*
import kotlinx.android.synthetic.main.item_medicine.view.*
import kotlinx.android.synthetic.main.item_prescription.view.*

class ChatPrescriptionViewHolder ( itemView: View) : BaseViewHolder<MedicineVO>(itemView) {

    var position : Long = 0
    init {




    }

    override fun bindData(data: MedicineVO) {
        mData = data
        itemView.tvChatPrescription.text = data.name



    }

    override fun position(id: Long)  {
        position = id
    }






}