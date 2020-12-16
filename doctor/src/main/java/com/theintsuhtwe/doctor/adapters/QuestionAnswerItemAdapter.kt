package com.theintsuhtwe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.delegates.ConsultationItemDelegate
import com.theintsuhtwe.doctor.views.viewholders.AnswerViewHolder
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder


class QuestionAnswerItemAdapter  (private val mDelegate: ConsultationItemDelegate) : BaseAdapter<BaseViewHolder<QuestionVO>, QuestionVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<QuestionVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_general_answer,parent,false)
        return AnswerViewHolder(
            mDelegate,
            v
        )
    }

}