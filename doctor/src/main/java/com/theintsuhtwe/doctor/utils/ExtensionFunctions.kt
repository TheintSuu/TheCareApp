package com.theintsuhtwe.doctor.utils

import com.theintsuhtwe.shared.data.vos.DoctorVO

fun saveDoctorToSession(doctor : DoctorVO) {
    doctor.id?.let {
        SessionManager.doctor_name = doctor.name
        SessionManager.doctor_id = doctor.id
        SessionManager.doctor_device_token = doctor.device_token
        SessionManager.doctor_speciality = doctor.specialities
        SessionManager.doctor_email = doctor.email
        SessionManager.doctor_photo = doctor.image.toString()
    }

}

