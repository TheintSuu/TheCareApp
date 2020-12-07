package com.theintsuhtwe.doctor.mvp.views

import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.views.BaseView

interface MainView : BaseView {

    fun displaySpecialQuestionByCateogry(category: List<QuestionVO>)
    fun displayGeneralQuestionByCateogry(category: List<QuestionVO>)
    fun displayMedicineQuestionByCateogry(category: List<MedicineVO>)
}