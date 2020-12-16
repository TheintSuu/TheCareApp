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

    override fun addSpecialityToDB(case: List<CategoryVO>){
       mTheCareDB.categoryDao().insertAllCategory(case)
    }

    override fun getSpecialityFromDB(id: String): LiveData<CategoryVO> {
     return mTheCareDB.categoryDao().getCategoryById(id)
    }

    override fun getAllSpecialityFromDB(): LiveData<List<CategoryVO>> {
        return mTheCareDB.categoryDao().getAllCategory()
    }

    override fun getCaseSummaryToDB(id: String): LiveData<CaseSummaryVO> {
      return  mTheCareDB.caseSummaryDao().getcase_summaryById(id)

    }



    override fun getDeviceToken(): String {
        return mFirebaseApi.getDeviceToken()
    }


    override fun getSpecailities(
        onSuccess: () -> Unit,
        onFaiure: (String) -> Unit
    ) {
       mFirebaseApi.getSpecialities(onSuccess={
           addSpecialityToDB(it)
           onSuccess()
       }, onFailure = {
           onFaiure(it)
       } )
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