package com.theintsuhtwe.shared.network

import com.theintsuhtwe.shared.data.vos.*

interface FirebaseApi {

    fun getSpecialities(onSuccess: (List<CategoryVO>) -> Unit,
                      onFailure: (String) -> Unit)

    fun getMedicinesBySpecialities(id : String, onSuccess: (List<MedicineVO>) -> Unit, onFailure: (String) -> Unit)

    fun getDoctorBySpecialities(id : String, onSuccess: (List<DoctorVO>) -> Unit,
                                onFailure: (String) -> Unit)


    fun getRecentlyDoctorByUser(id : String, onSuccess: (List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)


    fun getQuestions(onSuccess: (List<QuestionVO>) -> Unit,
                     onFailure: (String) -> Unit)

    fun getQuestionsByType(type : String, onSuccess: (List<QuestionVO>) -> Unit,
                           onFailure: (String) -> Unit)

    fun getQuestionsBySpecaility(id : String, onSuccess: (List<QuestionVO>) -> Unit,
                                 onFailure: (String) -> Unit)

    fun getConsultationByDoctor(id : String, onSuccess: (List<ConsultationVO>) -> Unit,
                                        onFailure: (String) -> Unit)

    fun getConsultationByPatient(id : String, onSuccess: (List<ConsultationVO>) -> Unit,
                                        onFailure: (String) -> Unit)

    fun  addDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit,
                                        onFailure: (String) -> Unit)

    fun  addPatient(patient: Patient, onSuccess: () -> Unit,
                    onFailure: (String) -> Unit)

    fun  addConsultations(con: ConsultationVO, onSuccess: () -> Unit,
                    onFailure: (String) -> Unit)

    fun sendDirectConsultationRequest(request: ConsultationRequest,  onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun sendMessage(id : String, message: MessageVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addPrescriptions(id : String, medicineVO: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun deletePrescriptions(id : String, medicineVO: List<MedicineVO> , onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun sendConsultationRequest( message:ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addRecentDoctorByPatient(id : String,  doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)


    fun createCaseSummary(case : CaseSummaryVO,  onSuccess:()->Unit, onFailure:(String)->Unit )


    fun  checkOutMedicine(checkOutVO: CheckOutVO
    , onSuccess: () -> Unit,
                   onFailure: (String) -> Unit)


    fun getDeviceToken() : String

    fun getDeviceTokenByType(  id: String,
                               onSuccess: (List<String>) -> Unit,
                               onFailure: (String) -> Unit)


}