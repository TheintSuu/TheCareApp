package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.CaseSummaryVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface CaseSummaryView : BaseView {

    fun displayGeneralQuestion(que : ArrayList<QuestionVO>)

    fun displayCaseSummary(que : ArrayList<QuestionVO>)

    fun  navigateToHome(requestId : String)
    
  


}