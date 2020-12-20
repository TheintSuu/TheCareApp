package com.theintsuhtwe.doctor.utils

import android.content.Context
import com.theintsuhtwe.shared.data.vos.DoctorVO
import com.theintsuhtwe.shared.network.response.DataVO
import com.theintsuhtwe.shared.network.response.NotificationVO

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

fun prepareNotificationForDoctor(context: Context, to:String?, doctorName : String, doctorId : String):NotificationVO{
    val notificationVO = NotificationVO()
    val dataVO = DataVO()
    notificationVO.to = to.toString()
    dataVO.title = "အကြောင်းကြားစာ"
    dataVO.body = "$doctorName မှ သင့်အား လက်ခံလိုက်ပါ ပြီ ယခုပင်ဆွေးနွေးမှုစတင်နိုင်ပါပြီ"
    dataVO.id = doctorId
    notificationVO.data = dataVO
    return notificationVO
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


