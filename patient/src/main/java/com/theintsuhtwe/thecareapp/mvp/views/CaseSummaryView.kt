package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface CaseSummaryView : BaseView {

    fun displayGeneralQuestion(que : List<QuestionVO>)

    fun displaySpecialQuestion(que : List<QuestionVO>)
    
  


}