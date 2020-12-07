package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl

object GeneralQuestionModelImpl : GeneralQuestionModel {

    private val mFirestore = CloudFirestoreFirebaseApiImpl


    override fun getQuestionsBySpecaility(
        id: String,
        onSuccess: (List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirestore.getQuestionsByType(id, onSuccess,onFailure)
    }

    override fun getQuestions(onSuccess: (List<QuestionVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirestore.getQuestions(onSuccess,onFailure)
    }

    override fun getQuestionsByType(type: String, onSuccess: (List<QuestionVO>) -> Unit, onFailure: (String) -> Unit) {
       return mFirestore.getQuestionsByType(type, onSuccess, onFailure)
    }

}