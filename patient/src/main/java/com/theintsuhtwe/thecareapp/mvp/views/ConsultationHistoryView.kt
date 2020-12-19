package com.theintsuhtwe.thecareapp.mvp.views

import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface ConsultationHistoryView : BaseView {

    fun displayCaseSummary(list: List<QuestionVO>)

    fun displayGeneral(list: List<QuestionVO>)

    fun displayHistory(list : List<ConsultationVO>)

    fun navigateToChat(id : String)

    fun displayEmptyView()

    fun showPrescriptionDialog(id : String, lisst : List<MedicineVO>)





    fun navigetToDetailPrescription(id : String)
}