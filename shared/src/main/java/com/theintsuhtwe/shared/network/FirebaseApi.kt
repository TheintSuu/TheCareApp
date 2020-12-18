package com.theintsuhtwe.shared.network

import com.theintsuhtwe.shared.data.vos.*

interface FirebaseApi {

    fun getSpecialities(onSuccess: (List<CategoryVO>) -> Unit,
                      onFailure: (String) -> Unit)

    fun getPatient(id : String, onSuccess: (patient : Patient) -> Unit,
                        onFailure: (String) -> Unit)

    fun getPatientByEmail(email : String, onSuccess: (patient : Patient) -> Unit,
                   onFailure: (String) -> Unit)

    fun getDoctorByEmail(email : String, onSuccess: (doctor : DoctorVO) -> Unit,
                          onFailure: (String) -> Unit)

    fun getMedicinesBySpecialities(id : String, onSuccess: (List<MedicineVO>) -> Unit, onFailure: (String) -> Unit)

    fun getDoctorBySpecialities(id : String, onSuccess: (List<DoctorVO>) -> Unit,
                                onFailure: (String) -> Unit)


    fun getRecentlyDoctorByUser(id : String, onSuccess: (List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)


    fun getQuestions(onSuccess: (List<QuestionVO>) -> Unit,
                     onFailure: (String) -> Unit)

    fun getQuestionsByType(type : String, onSuccess: (List<QuestionVO>) -> Unit,
                           onFailure: (String) -> Unit)

    fun getQuestionsByPateint(id : String, onSuccess: (ArrayList<QuestionVO>) -> Unit,
                           onFailure: (String) -> Unit)

    fun getQuestionsBySpecaility(id : String, onSuccess: (ArrayList<QuestionVO>) -> Unit,
                                 onFailure: (String) -> Unit)

    fun getConsultationByDoctor(id : String, onSuccess: (List<ConsultationVO>) -> Unit,
                                        onFailure: (String) -> Unit)

    fun getConsultationByPatient(id : String, onSuccess: (List<ConsultationVO>) -> Unit,
                                        onFailure: (String) -> Unit)

    fun  addDoctor(doctorVO: DoctorVO, onSuccess: () -> Unit,
                                        onFailure: (String) -> Unit)

    fun  addPatient(patient: Patient, onSuccess: () -> Unit,
                    onFailure: (String) -> Unit)

    fun  startConsultation(consulationId: String, dateTime: String, questionAnswerList: List<QuestionVO>, patientVO: Patient, doctorVO: DoctorVO, onSuccess: (String) -> Unit,
                           onFailure: (String) -> Unit)

    fun sendDirectConsultationRequest(request: ConsultationRequest,  onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun updateConsultationRequestByPatient(id : String,  onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun updateConsultationById(id : String,  onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun sendMessage(id : String, message: MessageVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getAllMessagesById(id : String,  onSuccess: (List<MessageVO>) -> Unit, onFailure: (String) -> Unit)

    fun sendQuestion(id : String, message: List<QuestionVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addPrescriptions(id : String, medicineVO: List<MedicineVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addPrescription(id : String, medicineVO: MedicineVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun deletePrescriptions(id : String, medicineVO: List<MedicineVO> , onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun sendConsultationRequest( recentId : String, patient : Patient,
                                 special: String,
                                caseSummaryId : ArrayList<QuestionVO> , onSuccess: (id : String) -> Unit, onFailure: (String) -> Unit)

    fun addRecentDoctorByPatient(id : String,  doctorVO: DoctorVO, onSuccess: () -> Unit, onFailure: (String) -> Unit)


    fun createCaseSummary(patient: Patient, special: String,list  : List<QuestionVO>,  onSuccess:(id : String)->Unit, onFailure:(String)->Unit )

    fun getCaseSummaryByPatient(id : String,  onSuccess:(case : CaseSummaryVO)->Unit, onFailure:(String)->Unit )


    fun  checkOutMedicine(address : String,
                          prescriptions : List<MedicineVO>,
                          totalAmout : String,
     onSuccess: () -> Unit,
                   onFailure: (String) -> Unit)


    fun getDeviceToken() : String

    fun getDeviceTokenByType(  id: String,
                               onSuccess: (List<String>) -> Unit,
                               onFailure: (String) -> Unit)

    fun getConfirmConsultation(id : String,onSuccess: (ConsultationRequest) -> Unit,
                               onFailure: (String) -> Unit)

    fun getConsultationRequestByDoctor(specail : String,onSuccess: (List<ConsultationRequest>) -> Unit,
                                       onFailure: (String) -> Unit)

    fun getConsultationRequestByDoctorId(id : String,onSuccess: (List<ConsultationRequest>) -> Unit,
                                       onFailure: (String) -> Unit)


    fun getConsultationById(id : String,onSuccess: (ConsultationVO) -> Unit,
                                       onFailure: (String) -> Unit)



    fun getConsultationRequestById(id : String,   onSuccess: (ConsultationRequest) -> Unit,
    onFailure: (String) -> Unit)

    fun updateConsultationRequestByDoctor(id: String ,onSuccess: () -> Unit,
                                       onFailure: (String) -> Unit)

    fun getPrescriptionByConsultationId(id : String,onSuccess: (List<MedicineVO>) -> Unit,
                            onFailure: (String) -> Unit)


    fun addConsultationNote(id : String, note: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getNoteByConsultationId(
        id: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )
}