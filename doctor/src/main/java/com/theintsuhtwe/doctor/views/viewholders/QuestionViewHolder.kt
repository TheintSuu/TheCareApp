package com.theintsuhtwe.doctor.views.viewholders

import android.view.View
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.delegates.QuestionDelegate
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_question_template.view.*

class  QuestionViewHolder (private val mDelegate: QuestionDelegate, itemView: View) : BaseViewHolder<QuestionVO>(itemView) {

    override fun bindData(data: QuestionVO) {

        data?.let {
            itemView.question_template.text = data.description
        }

        itemView.question_template.setOnClickListener {
            mDelegate.onTapOneQuestion(data)
        }
    }

    override fun position(id: Long) {

    }
}