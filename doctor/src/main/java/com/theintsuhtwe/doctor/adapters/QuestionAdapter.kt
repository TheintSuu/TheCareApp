package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.QuestionDelegate
import com.theintsuhtwe.doctor.views.viewholders.QuestionViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder

class QuestionAdapter (private val mDelegate: QuestionDelegate) :
    BaseAdapter<BaseViewHolder<QuestionVO>, QuestionVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<QuestionVO> {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_question_template, parent, false)
        return QuestionViewHolder(mDelegate, view)

    }
}