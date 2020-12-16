package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_general_answer.view.*


class AnswerViewHolder(private val mDelegate: ConsultationItemDelegate, itemView: View) : BaseViewHolder<QuestionVO>(itemView) {

    //var position : Long = 0
    init {
        itemView.setOnClickListener {
            mData?.let {

            }

        }
    }

    override fun bindData(data: QuestionVO) {
        mData = data


        itemView.tvDescription.text = data.description
        itemView.tvAnswer.text = ":\t\t\t\t\t\t\t"+data.answer



        //data.image.let { itemView.ivSpecialityImage.loadImage(it.toUri())}
    }

    override fun position(id: Long) {
        //position = 0
    }


}