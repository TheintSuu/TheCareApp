package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate

interface CaseSumaryQuestionView : BaseView {
    fun displayOneTimeQuestion(ques : List<QuestionVO>)

    fun displayOneTimeAnswer(ques : List<QuestionVO>)

    fun showAlwaysQuestion(id : String, req : String)

    fun navigaeToSpecialQuestion(id : String, req : String)
}