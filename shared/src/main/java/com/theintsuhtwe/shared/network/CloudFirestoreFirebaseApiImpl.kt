package com.theintsuhtwe.shared.network

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.utils.*
import java.util.*
import kotlin.collections.ArrayList

//import com.theintsuhtwe.shared.persistence.db.TheCareDB

object CloudFirestoreFirebaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference



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
        db.collection("patients").whereEqualTo("id", id)
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

    override fun getPatientByEmail(email: String, onSuccess: (patient: Patient) -> Unit, onFailure: (String) -> Unit) {
        db.collection("patients").whereEqualTo("email", email)
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

    override fun getDoctorByEmail(email: String, onSuccess: (doctor: DoctorVO) -> Unit, onFailure: (String) -> Unit) {
        db.collection("doctors").whereEqualTo("email", email)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check connection")
                    } ?: run {


                        val result = value?.documents ?: arrayListOf()

                        if(result.isNotEmpty()) {
                            val data = result?.first().data.convertToDoctorVO()
                            onSuccess(data)
                        }else{
                            onFailure("")
                        }



                    }

                }
    }

    override fun getMedicinesBySpecialities(
            id: String,
            onSuccess: (List<MedicineVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("specialities/${id}/medicines")
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
        onSuccess: (ArrayList<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patients/${id}/question")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val questionList:  ArrayList<QuestionVO> = arrayListOf()
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
            onSuccess: (ArrayList<QuestionVO>) -> Unit,
            onFailure: (String) -> Unit
    ) {
        db.collection("specialities/${id}/special_questions")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check internet connection")
                    } ?: run {
                        val questionList: ArrayList<QuestionVO> = arrayListOf()
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
        val root =    db.collection("consultation")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val doctorList: MutableList<ConsultationVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val consultation = document.data?.convertToConsultationVO()

                            if (consultation != null && consultation.patient?.id == id) {
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

        doctorVO.id?.let {
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
                "email" to patient.email,
                "image" to patient.image,
                "id" to patient.id,
                "phone" to patient.phone,
                "address" to patient.address
        )

        patient.id?.let {
            db.collection("patients")
                    .document(it)
                    .set(patientMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add patient")
                        onSuccess()
                    }
                    .addOnFailureListener {
                        Log.d("onFailure", "Failed to add patient", it)
                        onFailure("Failed to add patient")
                    }
        }
    }


    private fun updateConsultationRequest(consulationId: String, id: String, doc : DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit){
        val sfDocRef = db.collection("consultation_request").document("${consulationId}")

        db.runTransaction { transaction ->
            val snapshot = transaction.get(sfDocRef)

            transaction.update(sfDocRef, "status", "accept")
            transaction.update(sfDocRef, "consultation_chat_id", id)
            transaction.update(sfDocRef, "doctor", doc)




        }.addOnSuccessListener { result ->
            onSuccess()
            Log.d("Status Update", "Transaction success: $result")
        }.addOnFailureListener { e ->
            Log.w("Status Update", "Transaction failure.", e)
        }
    }
    override fun startConsultation(
            consulationId: String, dateTime: String, questionAnswerList: List<QuestionVO>, patientVO: Patient, doctorVO: DoctorVO,
            onSuccess: (String) -> Unit,
            onFailure: (String) -> Unit
    ) {
        val id =  UUID.randomUUID().toString()


        val consultationMap = hashMapOf(
                "id" to id,
                "create_at" to dateTime,
                "speciality" to doctorVO.specialities,
                "doctor" to doctorVO,
                "case_summary" to questionAnswerList,
                "patient" to patientVO,
                "consultation_status" to "start"


        )
        id?.let {conId ->
            db.collection("consultation")
                    .document(conId)
                    .set(consultationMap)
                    .addOnSuccessListener {
                        Log.d("success", "Successfully add doctor")
                            onSuccess(id)
                            updateConsultationRequest(consulationId,id, doctorVO, onSuccess = {
                                onSuccess(id)
                            }, onFailure = {

                            })




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

    override fun updateConsultationRequestByPatient(id: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val sfDocRef = db.collection("consultation_request").document("${id}")

        db.runTransaction { transaction ->
            val snapshot = transaction.get(sfDocRef)

            transaction.update(sfDocRef, "status", "finish")

        }.addOnSuccessListener { result ->
            onSuccess()
            Log.d("Status Update", "Transaction success: $result")
        }.addOnFailureListener { e ->
            Log.w("Status Update", "Transaction failure.", e)
        }
    }

    override fun updateConsultationById(id: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val sfDocRef = db.collection("consultation").document("${id}")

        db.runTransaction { transaction ->
            val snapshot = transaction.get(sfDocRef)

            transaction.update(sfDocRef, "consultation_status", "finish")

        }.addOnSuccessListener { result ->
            onSuccess()
            Log.d("Status Update", "Transaction success: $result")
        }.addOnFailureListener { e ->
            Log.w("Status Update", "Transaction failure.", e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun sendMessage(
            id: String,
            message: MessageVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {

        val messageMap = hashMapOf(
                "text" to message.text,
                "image" to message.image,
                "sender_id" to message.senderId,
                "sender_image" to message.senderImage,
                "sender_at" to  currentDate(),
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

    override fun getAllMessagesById(
        id: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${id}/messages")
            .addSnapshotListener { value, error ->
                error?.let {

                    onFailure(it.message ?: "Please check connection")
                } ?: run {

                    val messages: MutableList<MessageVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data
                        val message = data.convertToMessageVO()
                        messages.add(message)
                    }

                    onSuccess(messages)
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

    override fun addPrescription(id: String, medicineVO: MedicineVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val prescriptions = hashMapOf(
               "id" to medicineVO.id,
                "name" to medicineVO.name,
                "price" to medicineVO.price,
                "quantity" to medicineVO.quantity,
                "sub_total" to medicineVO.sub_total,
            "total_day" to medicineVO.total_day,
                "note" to medicineVO.note,
                "repeat" to medicineVO.repeat
        )
        medicineVO.id?.let {
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

    override fun addPrescriptions(id: String, medicines: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {

        medicines.forEach {
                medicineVO ->
            addPrescription(id, medicineVO, onSuccess, onFailure)

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
            recentId : String,
            patient : Patient,
            special: String,
            case :ArrayList<QuestionVO> ,
            onSuccess: (id : String) -> Unit,
            onFailure: (String) -> Unit
    ) {

        if(recentId.length>0){
            val id = UUID.randomUUID().toString()

            val consultationMap = hashMapOf(
                "id" to id,
                "recent_doctor_id" to recentId,
                "patient" to patient,
                "specialities" to special,
                "status" to "all",
                "case_summary" to case

            )

            id?.let {
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
        }else{
            val id = UUID.randomUUID().toString()

            val consultationMap = hashMapOf(
                "id" to id,
                "patient" to patient,
                "specialities" to special,
                "status" to "all",
                "case_summary" to case

            )

            id?.let {
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

    }

    override fun addRecentDoctorByPatient(id: String, doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
       val docuID = UUID.randomUUID().toString()
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

        getRecentlyDoctorByUser(id, onSuccess = {

        }, onFailure = {

        })
        docuID?.let {
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
            address : String,
            prescriptions : List<MedicineVO>,
            totalAmout : String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        val id = UUID.randomUUID().toString()
        val checkout = hashMapOf(
            "id"  to id,
                "prescriptions" to prescriptions,
                "total_amount" to totalAmout,
                "address" to address

        )

        id.let {
            db.collection("checkout")
                    .document(it)
                    .set(checkout)
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
        db.collection("consultation_request").whereEqualTo("status", "accept").whereEqualTo("id", id)
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

        db.collection("consultation_request").whereEqualTo("status", "all").whereEqualTo("specialities", special)
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

    override fun getConsultationRequestByDoctorId(
        id: String,
        onSuccess: (List<ConsultationRequest>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request").whereEqualTo("status", "recent").whereEqualTo("recent_doctor_id", id)
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

    override fun getConsultationById(
        id: String,
        onSuccess: (ConsultationVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val root =    db.collection("consultation").whereEqualTo("id", id)
            .addSnapshotListener { value, error ->
                error?.let {

                    onFailure(it.message ?: "Please check connection")
                } ?: run {


                    val result = value?.documents ?: arrayListOf()

                            if(result.size>0){
                                val consultation = result.first().data?.convertToConsultationVO()
                                consultation?.let { onSuccess(it) }
                            }
                }
            }
    }

    override fun getConsultationRequestById(id: String, onSuccess: (ConsultationRequest) -> Unit, onFailure: (String) -> Unit) {
        db.collection("consultation_request").whereEqualTo("id", id).whereEqualTo("status","all")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val request: MutableList<ConsultationRequest> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()


                        when(result.size > 0){
                            true -> {
                                val req = result.first().data.convertToConsultationRequest()
                                onSuccess(req)
                            }else -> {

                        }

                        }

                    }
                }
    }

    override fun updateConsultationRequestByDoctor(id:  String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        db.collection("consultation_request/${id}")
                .addSnapshotListener { value, error ->
                    error?.let {

                        onFailure(it.message ?: "Please check connection")
                    } ?: run {
                        val request: MutableList<ConsultationRequest> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        if(result.isNotEmpty()) {
                            val consultationRequest = result.first().data.convertToConsultationRequest()
                            consultationRequest.status = "accept"

                        }

                        }
                    }
                }

    override fun getPrescriptionByConsultationId(
        id: String,
        onSuccess: (List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation/${id}/prescriptions")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {

                    val medicines: MutableList<MedicineVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val category = document.data?.convertToMedicinesVO()

                        category?.let { medicines.add(it) }
                    }
                    onSuccess(medicines)
                }
            }

    }

    override fun getNoteByConsultationId(
        id: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation").whereEqualTo("id", id)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {




                    val result = value?.documents ?: arrayListOf()

                    var note : String = ""
                    for (document in result) {
                         note = document.data?.convertToNote().toString()


                    }
                    onSuccess(note)
                }
            }

    }

    override fun addConsultationNote(
        id: String,
        note: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val sfDocRef = db.collection("consultation").document("${id}")

        db.runTransaction { transaction ->
            val snapshot = transaction.get(sfDocRef)

            transaction.update(sfDocRef, "consultation_note", note)



        }.addOnSuccessListener { result ->
            Log.d("Status Update", "Transaction success: $result")
        }.addOnFailureListener { e ->
            Log.w("Status Update", "Transaction failure.", e)
        }
    }


}