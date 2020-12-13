package com.theintsuhtwe.shared.data.models

import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl
import com.theintsuhtwe.shared.network.FirebaseApi
import java.util.*
import kotlin.collections.ArrayList

object SpecialitiesModelImpl : SpecialitiesModel, BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun addCaseSummaryToDB(case: ArrayList<QuestionVO>):String {
        mTheCareDB.caseSummaryDao().deleteAll()
        val id = UUID.randomUUID().toString()
        mTheCareDB.caseSummaryDao().insertcase_summary(CaseSummaryVO(id = id, questionList = case))
        return id
    }

    override fun getCaseSummaryToDB(id: String): LiveData<CaseSummaryVO> {
      return  mTheCareDB.caseSummaryDao().getcase_summaryById(id)

    }

//    override fun GeneralQuestionDeletAndInsertToDB(case: ArrayList<QuestionVO>) {
//        mTheCareDB.genearlQuestionDao().deleteAll()
//        mTheCareDB.genearlQuestionDao().insertAllQuestion(case)
//    }

//    override fun addGeneralQuestionToDB(case: ArrayList<QuestionVO>) {
//        mTheCareDB.genearlQuestionDao().insertAllQuestion(case)
//    }

//    override fun getGeneralQuestionToDB(): ArrayList<QuestionVO> {
//       return mTheCareDB.genearlQuestionDao().getAllQuestion()
//    }

    override fun getDeviceToken(): String {
        return mFirebaseApi.getDeviceToken()
    }


    override fun getSpecailities(
        onSuccess: (List<CategoryVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
       mFirebaseApi.getSpecialities(onSuccess, onFaiure)
    }



    override fun getMedicinesBySpecailities(
            id: String,
            onSuccess: (List<MedicineVO>) -> Unit,
            onFaiure: (String) -> Unit
    ) {
       mFirebaseApi.getMedicinesBySpecialities(id, onSuccess, onFaiure)
    }

    override fun getSpecialQuestionsBySpecailities(
        id: String,
        onSuccess: (ArrayList<QuestionVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
       mFirebaseApi.getQuestionsBySpecaility(id, onSuccess, onFaiure)
    }

    override fun getGeneralQuestionsBySpecailities(
        id: String,
        onSuccess: (List<QuestionVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
        mFirebaseApi.getQuestionsByType(id, onSuccess, onFaiure)
    }

    override fun getDoctorBySpecialities(
        id: String,
        onSuccess: (List<DoctorVO>) -> Unit,
        onFaiure: (String) -> Unit
    ) {
       mFirebaseApi.getDoctorBySpecialities(id,  onSuccess, onFaiure)
    }


}