package com.theintsuhtwe.thecareapp.views.viewholders

import android.view.View
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_answer.view.*
import kotlinx.android.synthetic.main.item_special_question.view.tvNumber

class SpecialAnswerViewHolder (private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<QuestionVO>(itemView) {

    var position : Long = 0
    init {
        itemView.setOnClickListener {
            mData?.let {
                it.description?.let { it1 -> mDelegate.onTapSpecialities(it1) }
            }

        }
    }

    override fun bindData(data: QuestionVO) {
        mData = data
        itemView.tvDescription.text = data.description
        itemView.tvAnswer.text = data.answer
        itemView.tvNumber.text = ( position + "1".toLong()  ).toString() +")."




    }

    override fun position(id: Long) {
        position = id
    }


}