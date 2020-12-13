package com.theintsuhtwe.shared.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.network.FirebaseApi
import com.theintsuhtwe.shared.network.auth.AuthManager

interface SpecialitiesModel {
    var mFirebaseApi : FirebaseApi

    fun addCaseSummaryToDB (case : ArrayList<QuestionVO>) : String
    fun getCaseSummaryToDB(id : String) : LiveData<CaseSummaryVO>
  // fun GeneralQuestionDeletAndInsertToDB (case : ArrayList<QuestionVO>)
   // fun addGeneralQuestionToDB(case: ArrayList<QuestionVO>)
//    fun getGeneralQuestionToDB() : ArrayList<QuestionVO>
    fun getDeviceToken() : String
    fun getSpecailities(onSuccess: (List<CategoryVO>) -> Unit, onFaiure: (String) -> Unit)
    fun getMedicinesBySpecailities(id: String, onSuccess: (List<MedicineVO>) -> Unit, onFaiure: (String) -> Unit)
    fun getSpecialQuestionsBySpecailities(id : String, onSuccess: (ArrayList<QuestionVO>) -> Unit, onFaiure: (String) -> Unit)
    fun getGeneralQuestionsBySpecailities(id : String, onSuccess: (List<QuestionVO>) -> Unit, onFaiure: (String) -> Unit)
    fun getDoctorBySpecialities(id : String,onSuccess: (List<DoctorVO>) -> Unit, onFaiure: (String) -> Unit)


}