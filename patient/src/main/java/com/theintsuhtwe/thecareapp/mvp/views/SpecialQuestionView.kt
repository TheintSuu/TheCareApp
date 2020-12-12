package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface SpecialQuestionView : BaseView {
    
    fun displayQuestionBySpeciality(ques : List<QuestionVO>)

    fun navigateToCaseSummary(summaryId : String)

    fun addQuestionToCaseSummary(ques : QuestionVO)


}