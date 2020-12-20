package com.theintsuhtwe.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.BasePresenter
import com.theintsuhtwe.thecareapp.delegates.SpecialitiesItemDelegate
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView

interface CaseSummaryQuestionPresenter :  BasePresenter<CaseSumaryQuestionView>,
    SpecialitiesItemDelegate {
    fun onUiReady(id : String, life : LifecycleOwner)
    fun onTapNext(id : String, type : String, special : String, list : ArrayList<QuestionVO>)
    fun onTapNextOnce(id : String, type : String,name : String, bdd : String, phone : String, address : String, special : String, list : ArrayList<QuestionVO>)
}