package com.theintsuhtwe.doctor.utils

import com.theintsuhtwe.shared.data.vos.DoctorVO

fun saveDoctorToSession(doctor : DoctorVO) {
    doctor.id?.let {
        SessionManager.doctor_name = doctor.name
        SessionManager.doctor_image = doctor.image
        SessionManager.doctor_id = doctor.id
        SessionManager.doctor_device_token = doctor.device_token
        SessionManager.doctor_speciality = doctor.specialities
        SessionManager.doctor_email = doctor.email

    }

}

 fun totalMedicines( quantitly : String, dayWeek : Int , beforAter : Int) : Int{
    quantitly.toInt()?.let{
        return quantitly.toInt() * dayWeek * beforAter
    }
    return 1
}

  fun subTotalMedicinePrice(quan : Int , price : Int) : Int{
    return quan * price
}

//Error Messages
const val EM_NO_INTERNET_CONNECTION = "No Internet Connection"
const val EM_NO_NEWS_AVAILABLE = "There are no news available"


const val  FINISH = "FINISH"

const val SIGN_OUT = "SIGN_OUT"
const val SIGN_IN = "SIGN_IN"
//Empty Image
const val EMPTY_IMAGE_URL = "https://cdn.dribbble.com/users/888330/screenshots/2653750/empty_data_set.png"


