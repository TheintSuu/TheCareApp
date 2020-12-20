package com.theintsuhtwe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.theintsuhtwe.doctor.adapters.QuestionAdapter
import com.theintsuhtwe.doctor.mvp.views.QuestionView
import com.theintsuhtwe.shared.data.models.DoctorModel
import com.theintsuhtwe.shared.data.models.DoctorModelImpl
import com.theintsuhtwe.shared.data.models.SpecialitiesModel
import com.theintsuhtwe.shared.data.models.SpecialitiesModelImpl
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.mvp.presenters.AbstractBasePresenter

class QuestionPresenterImpl: QuestionPresenter , AbstractBasePresenter<QuestionView>() {

    private val doctorModel: DoctorModel = DoctorModelImpl

    private val specaillModel : SpecialitiesModel = SpecialitiesModelImpl

    override fun onUiReady(special : String) {

        specaillModel.getSpecialQuestionsBySpecailities(
            special,
            onSuccess = {
                mView?.displayGeneralQuestions(it)
            },
            onFaiure = {

            }
        )
       
    }



    override fun onTapOneQuestion(gQuestionVO: QuestionVO) {
        mView?.navigateToToChatRoom(gQuestionVO.description.toString())
    }
}