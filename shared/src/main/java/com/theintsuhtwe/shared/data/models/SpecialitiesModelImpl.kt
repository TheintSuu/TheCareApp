package com.theintsuhtwe.shared.data.models

import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.network.CloudFirestoreFirebaseApiImpl
import com.theintsuhtwe.shared.network.FirebaseApi

object SpecialitiesModelImpl : SpecialitiesModel {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
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
        onSuccess: (List<QuestionVO>) -> Unit,
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