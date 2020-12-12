package com.theintsuhtwe.shared.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.utils.*
import java.util.*

//import com.theintsuhtwe.shared.persistence.db.TheCareDB

object CloudFirestoreFirebaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    //private lateinit var mTheCareDB : TheCareDB

    override fun getDoctorBySpecialities(
            id: String,
            onSuccess: (List<DoctorVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("doctors").whereEqualTo("categoryId", id)
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val doctorList: MutableList<DoctorVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data
                            val doctor = DoctorVO()
                            doctor.name = data?.get("name") as String
//                        doctor.cateogryId = data?.get("categoryId") as String

                            doctorList.add(doctor)
                        }

                        onSuccess(doctorList)
                    }
                }

    }


    override fun getSpecialities(
            onSuccess: (List<CategoryVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("specialities")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val categoryList: MutableList<CategoryVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val category = document.data?.convertToSpecialities()

                            category?.let { categoryList.add(it) }
                        }
                        onSuccess(categoryList)
                    }
                }
    }

    override fun getPatient(id : String, onSuccess: (patient: Patient) -> Unit, onFailure: (String) -> Unit) {
        db.collection("patients").whereEqualTo("email", id)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {


                    val result = value?.documents ?: arrayListOf()

                        if(result.isNotEmpty()) {
                            val data = result?.first().data.convertToPatientVO()
                            onSuccess(data)
                        }



                    }

                }

    }

    override fun getMedicinesBySpecialities(
            id: String,
            onSuccess: (List<MedicineVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("specialities/${id}/medicine")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check internet connection")
                    } ?: run {
                        val medicineList: MutableList<MedicineVO> = arrayListOf()
                        val result = value?.documents ?: arrayListOf()
                        for (document in result) {
                            val data = document?.data.convertToMedicineVO()
                            medicineList.add(data)
                        }
                        onSuccess(medicineList)
                    }

                }
    }


    override fun getRecentlyDoctorByUser(
            id: String,
            onSuccess: (List<DoctorVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("patients/${id}/recent_doctors")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val doctorList: MutableList<DoctorVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val doctor = document.data?.convertToDoctorVO()
                            if (doctor != null) {
                                doctorList.add(doctor)
                            }
                        }

                        onSuccess(doctorList)
                    }
                }

    }


    override fun getQuestions(onSuccess: (List<QuestionVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("general_questions").whereEqualTo("type", "once")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check internet connection")
                    } ?: run {
                        val questionList: MutableList<QuestionVO> = arrayListOf()
                        val result = value?.documents ?: arrayListOf()
                        for (document in result) {
                            val data = document?.data.convertToQuestionVO()
                            questionList.add(data)
                        }
                        onSuccess(questionList)
                    }

                }
    }

    override fun getQuestionsByType(
            type: String,
            onSuccess: (List<QuestionVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("general_questions").whereEqualTo("type", type)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check internet connection")
                    } ?: run {
                        val questionList: MutableList<QuestionVO> = arrayListOf()
                        val result = value?.documents ?: arrayListOf()
                        for (document in result) {
                            val data = document?.data.convertToQuestionVO()
                            questionList.add(data)
                        }
                        onSuccess(questionList)
                    }

                }

    }

    override fun getQuestionsByPateint(
        id: String,
        onSuccess: (List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patients/${id}/question")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val questionList: MutableList<QuestionVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document?.data.convertToQuestionVO()
                        questionList.add(data)
                    }
                    onSuccess(questionList)
                }

            }
    }

    override fun getQuestionsBySpecaility(
            id: String,
            onSuccess: (List<QuestionVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("specialities/${id}/special_questions")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check internet connection")
                    } ?: run {
                        val questionList: MutableList<QuestionVO> = arrayListOf()
                        val result = value?.documents ?: arrayListOf()
                        for (document in result) {
                            val data = document?.data.convertToQuestion()
                            questionList.add(data)
                        }
                        onSuccess(questionList)
                    }

                }

    }

    override fun getConsultationByDoctor(id: String, onSuccess: (List<ConsultationVO>) -> Unit, onFailure: (String) -> Unit) {
     val root =    db.collection("consultation")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val doctorList: MutableList<ConsultationVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val consultation = document.data?.convertToConsultationVO()

                            if (consultation != null && consultation.doctor?.id == id) {
                                doctorList.add(consultation)
                            }
                        }

                        onSuccess(doctorList)
                    }
                }
    }

    override fun getConsultationByPatient(id: String, onSuccess: (List<ConsultationVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("consultation").document("patient").collection("id")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val doctorList: MutableList<ConsultationVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val consultation = document.data?.convertToConsultationVO()
                            if (consultation != null) {
                                doctorList.add(consultation)
                            }
                        }

                        onSuccess(doctorList)
                    }
                }

    }

    override fun addDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val doctorMap = hashMapOf(
                "name" to doctorVO.name,
                "id" to doctorVO.id,
                "image" to doctorVO.image,
                "email" to doctorVO.email,
                "phone" to doctorVO.phone,
                "biography" to doctorVO.biography,
                "device_token" to doctorVO.device_token,
                "degrees" to doctorVO.degrees,
                "specialities" to doctorVO.specialities

        )

        doctorVO.name?.let {
            db.collection("doctors")
                    .document(it)
                    .set(doctorMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add doctor")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add doctor")
                        onFailure("Failed to add doctor")
                    }
        }

    }

    override fun addPatient(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val patientMap = hashMapOf(
                "name" to patient.name,
                "email" to patient.id,
                "image" to patient.image,
                "id" to patient.id,
                "phone" to patient.phone,
                "address" to patient.address
        )

        patient.name?.let {
            db.collection("patients")
                    .document(it)
                    .set(patientMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add patient")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add patient")
                        onFailure("Failed to add patient")
                    }
        }
    }

    override fun addConsultations(
            con: ConsultationVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {

        val consultationMap = hashMapOf(
                "id" to con.id,
                "doctor" to con.doctor,
                "case_summary" to con.caseSummary,
                "patient" to con.patient


        )
        con.id?.let {
            db.collection("consultation")
                    .document(it)
                    .set(consultationMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add doctor")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add doctor")
                        onFailure("Failed to add doctor")
                    }

        }
    }

    override fun sendDirectConsultationRequest(request: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val consultationMap = hashMapOf(
                "id" to request.id,
                "patient" to request.patient,
                "doctor" to request.doctor,
                "case_summary" to request.caseSummary
        )

        request.id?.let {
            db.collection("consultation_request")
                    .document(it)
                    .set(consultationMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add doctor")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add doctor")
                        onFailure("Failed to add doctor")
                    }
        }
    }

    override fun sendMessage(
            id: String,
            message: MessageVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {

        val messageMap = hashMapOf(
                "text " to message.text,
                "image" to message.image,
                "senderId" to message.senderType,
                "id" to message.id

        )

        message?.id.let {
            db.collection("consultation/${id}/messages")
                    .document(it)
                    .set(messageMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add messages")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add messages")
                        onFailure("Failed to add messages")
                    }
        }

    }

    override fun sendQuestion(
        id: String,
         ques : List<QuestionVO>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        ques.forEach {
            val messageMap = hashMapOf(
                "description " to it.description,
                "answer" to it.answer,
                "type" to it.type

            )
            it?.description.let {
                db.collection("patients/${id}/questions")
                    .document(it)
                    .set(messageMap)
                    .addOnSuccessListener {

                        Log.d("success", "Successfully add question")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add question")
                        onFailure("Failed to add question")
                    }
            }
        }

    }

    override fun addPrescriptions(id: String, medicineVO: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val prescriptions = arrayOf(
                medicineVO
        )
        id.let {
            db.collection("consultation/${id}/prescriptions")
                    .document(it)
                    .set(prescriptions)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add messages")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add messages")
                        onFailure("Failed to add messages")
                    }
        }
    }

    override fun deletePrescriptions(id: String, medicineVO: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val prescriptions = arrayOf(
                medicineVO
        )
        id.let {
            db.collection("consultation/${id}/prescriptions")
                    .document(it)
                    .delete()
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add messages")
                        onFailure("Failed to add messages")
                    }
        }
    }

    override fun sendConsultationRequest(
            patient : Patient,
            special: String,
            id : String ,
            onSuccess: (id : String) -> Unit,
            onFailure: (String) -> Unit
    ) {

//        val patient = Patient(
//            id = patientId,
//            name = "Hazel",
//            age = ""
//        )
        val id = UUID.randomUUID().toString()
        val consultationMap = hashMapOf(
                "id" to id,
                "patient" to patient,
            "specialities" to special

        )
        getCaseSummaryByPatient(patient.id, onSuccess = {

        },
                onFailure = {
                    consultationMap.set("case_summary", it)
                }
        )
        patient.id?.let {
            db.collection("consultation_request")
                    .document(it)
                    .set(consultationMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add doctor")
                        onSuccess(id)
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add doctor")
                        onFailure("Failed to add doctor")
                    }
        }
    }

    override fun addRecentDoctorByPatient(id: String, doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val doctorMap = arrayOf(
                "recent_doctors" to doctorVO
        )
        id?.let {
            db.collection("patients/${id}/recent_doctors")
                    .document(it)
                    .set(doctorMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add patient")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add patient")
                        onFailure("Failed to add patient")
                    }
        }
    }

    override fun createCaseSummary(patient: Patient, special: String,list  : List<QuestionVO>, onSuccess: (id : String) -> Unit, onFailure: (String) -> Unit) {
        val id = UUID.randomUUID().toString()
        val caseSummary = hashMapOf(
                "id" to id,
                "questions" to list
        )
        id?.let {
            db.collection("case_summary")
                    .document(it)
                    .set(caseSummary)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add doctor")
                        sendConsultationRequest(patient, special, id, onSuccess ={
                            onSuccess(it)
                        }, onFailure = {

                        })
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add doctor")
                        onFailure("Failed to add doctor")
                    }

        }
    }

    override fun getCaseSummaryByPatient(id: String, onSuccess: (case: CaseSummaryVO) -> Unit, onFailure: (String) -> Unit) {
        db.collection("case_summary/${id}")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check internet connection")
                    } ?: run {

                        val result = value?.documents ?: arrayListOf()
                        when(result.isNotEmpty()){
                            true -> {
                                val data = result.first().data.convertToCaseSummary()
                                onSuccess(data)
                            }


                        }

                    }

                }
    }

    override fun checkOutMedicine(
            checkOutVO: CheckOutVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        val doctorMap = hashMapOf(
                "prescription" to checkOutVO.medicine,
                "id" to checkOutVO.id,
                "delivery_routine" to checkOutVO.delivery_routine,
                "total_amount" to checkOutVO.total_amount,
                "address" to checkOutVO.address
        )

        checkOutVO?.id.let {
            db.collection("checkout")
                    .document(it)
                    .set(doctorMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add checkout")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add checkout")
                        onFailure("Failed to add checkout")
                    }
        }
    }

    override fun getDeviceToken(): String {

        var token: String? = null
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("fbToken", it.token)
            token = it.token
        }
        return token.toString()
    }

    override fun getDeviceTokenByType(type: String,
                                      onSuccess: (List<String>) -> Unit,
                                      onFailure: (String) -> Unit){
        db.collection("type")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val tokenList: MutableList<String> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data.toString()

                            tokenList.add(data)
                        }

                        onSuccess(tokenList)
                    }

                }
    }

    override fun getConfirmConsultation(
        id : String,
        onSuccess: (ConsultationRequest) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request").whereEqualTo("status", "accept").whereEqualTo("id", "request000")
            .addSnapshotListener { value, error ->
                error?.let {

                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val request: ConsultationRequest = ConsultationRequest()

                 if(value?.documents?.isNotEmpty()!!){
                        val result = value?.documents?.first()


                        val data = result?.data.convertToConsultationRequest()


                        onSuccess(data)
                    }

                }
            }
    }


    override fun getConsultationRequestByDoctor(
        special : String,
        onSuccess: (List<ConsultationRequest>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request").whereEqualTo("status", "new").whereEqualTo("specialities", special)
            .addSnapshotListener { value, error ->
                error?.let {

                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val request: MutableList<ConsultationRequest> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data.convertToConsultationRequest()


                        request.add(data)
                    }

                    onSuccess(request)
                }
            }
    }


}