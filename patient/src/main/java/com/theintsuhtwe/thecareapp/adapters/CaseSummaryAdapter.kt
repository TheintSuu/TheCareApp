package com.theintsuhtwe.thecareapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.theintsuhtwe.shared.adapters.BaseAdapter
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.views.viewholders.AnswerViewHolder
import com.theintsuhtwe.thecareapp.views.viewholders.SpecialAnswerViewHolder

class CaseSummaryAdapter (private val mDelegate: SpecialitiesItemDelegate) : BaseAdapter<BaseViewHolder<QuestionVO>, QuestionVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<QuestionVO> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_answer,parent,false)
        return  SpecialAnswerViewHolder(mDelegate,v)
    }

}