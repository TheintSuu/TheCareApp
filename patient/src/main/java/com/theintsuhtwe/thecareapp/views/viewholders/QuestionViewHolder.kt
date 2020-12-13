package com.theintsuhtwe.thecareapp.views.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.viewholders.BaseViewHolder
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import kotlinx.android.synthetic.main.item_general_answer.view.*
import kotlinx.android.synthetic.main.item_general_answer.view.tvDescription
import kotlinx.android.synthetic.main.item_special_question.view.*
import kotlinx.android.synthetic.main.item_speciality.view.*

class QuestionViewHolder (private val mDelegate: SpecialitiesItemDelegate, itemView: View) : BaseViewHolder<QuestionVO>(itemView) {

    var position : Long = 0
    init {




    }

    override fun bindData(data: QuestionVO) {
        mData = data

        itemView.tvDescription.text = data.description

        itemView.tvNumber.text = ( position + "1".toLong()  ).toString() +")."

        itemView.etAnswer.addTextChangedListener(object: TextWatcher {override fun afterTextChanged(s: Editable?) {

        }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mData?.let {
                    mDelegate.onTapQuestion(it.description, s.toString())
                }
            }

        })
    }

    override fun position(id: Long)  {
        position = id
    }






}