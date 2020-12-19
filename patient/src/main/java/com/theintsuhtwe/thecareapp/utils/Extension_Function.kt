package com.theintsuhtwe.thecareapp.utils

import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.data.vos.QuestionVO


//Error Messages
const val EM_NO_INTERNET_CONNECTION = "No Internet Connection"
const val EM_NO_NEWS_AVAILABLE = "There are no news available"

const val FINISH = "FINISH"

//Empty Image
const val EMPTY_IMAGE_URL = "https://cdn.dribbble.com/users/888330/screenshots/2653750/empty_data_set.png"

const val DEL_FEE = 2000
fun getCurrentPatientInfo(): Patient {
        val patient = Patient()
        patient.id = SessionManager.patient_id.toString()
        patient.image = SessionManager.patient_photo.toString()
        patient.name = SessionManager.patient_name.toString()
        patient.email = SessionManager.patient_email.toString()
        patient.device_token = SessionManager.patient_device_token.toString()
//        val que : ArrayList<QuestionVO> = arrayListOf()
//        que.add(QuestionVO(description = "သွေးပေါင်ချိန်", answer = SessionManager.patient_bloodPressure.toString()))
//        que.add(QuestionVO(description = "သွေးအမျိုးအစား", answer = SessionManager.patient_bloodType.toString()))
//        que.add(QuestionVO(description = "မွေးနေ့", answer = SessionManager.patient_dateOfBirth.toString()))
//        que.add(QuestionVO(description = "ပေါင်ချိန်", answer = SessionManager.patient_weight.toString()))
//        que.add(QuestionVO(description = "အရပ်", answer = SessionManager.patient_height.toString()))
//        que.add(QuestionVO(description = "မတည့်သော ဆေးအမျိုးအစား", answer = SessionManager.patient_comment.toString()))
//        patient.question = que

    return  patient
}




