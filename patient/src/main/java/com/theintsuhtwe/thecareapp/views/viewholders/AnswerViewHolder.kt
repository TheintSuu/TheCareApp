package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_general_answer.view.*
import kotlinx.android.synthetic.main.item_speciality.view.*

class AnswerViewHolder(private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<QuestionVO>(itemView) {

    //var position : Long = 0
    init {
        itemView.setOnClickListener {
            mData?.let {
                mDelegate.onTapSpecialities(it.description)
            }

        }
    }

    override fun bindData(data: QuestionVO) {
        mData = data


        itemView.tvDescription.text = data.description
        itemView.tvAnswer.text = data.answer



        //data.image.let { itemView.ivSpecialityImage.loadImage(it.toUri())}
    }

    override fun position(id: Long) {
        //position = 0
    }


}