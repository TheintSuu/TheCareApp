 package com.theintsuhtwe.shared.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.bumptech.glide.Glide
import com.google.common.reflect.TypeToken
import com.google.firebase.Timestamp
import com.google.firebase.firestore.auth.User
import com.google.gson.Gson
import com.theintsuhtwe.shared.data.vos.*
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

 fun randomImage() : String{
    var img : MutableList<String> = arrayListOf()
    img.add("https://previews.123rf.com/images/yupiramos/yupiramos1707/yupiramos170726578/82990209-surgeon-avatar-character-icon-vector-illustration-design.jpg")
    img.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1CKDWyfyCPo6iyesi_slg-zPaPqRz2wJv_A&usqp=CAU")
    img.add("https://image.freepik.com/free-vector/doctor-icon-avatar-white_136162-58.jpg")
    img.add("https://cdn3.iconfinder.com/data/icons/avatar-profession-2/512/Avatar_17_-_Professions_-_Background-02-512.png")

    return img.random()
}


fun createNotiRequsetBody(to : String, body : String, title : String) : JSONObject
{

        val data = JSONObject()
        data.put("name", "Professor U Aung Win")
        data.put("specialities", "Dental")
        data.put("biography", "Body of FCM Notification")


        val jsonObj_ = JSONObject()
        jsonObj_.put("to", "eXtOYb6USvWuty6lDtynZ-:APA91bHry8SrjUJSkD2XyV-xX0zJFlgbysrUaSwingfiKEi6K5mBRtwW8PIFT0MED_4m_R6tSxRdFleiHAuIPbkVlRUoTDxh2nrJ5JygvIG5o9asyTcd85Sg29BHmkDI37iiuaihhsU_")
        jsonObj_.put("collapse_key", Collapse_Key)
        jsonObj_.put("data", data)

    return jsonObj_


}



fun MutableMap<String,Any>?.convertToQuestionVO(): QuestionVO {
    val question = QuestionVO()
    question.description = this?.get("description") as String ?
    question.answer = this?.get("answer") as String ?



    return question
}

fun MutableMap<String,Any>?.convertToQuestion(): QuestionVO {
    val question = QuestionVO()
    question.description = this?.get("description") as String
    return question
}

fun MutableMap<String,Any>?.convertToAddress(): Address{
    val address = Address()
    address.state =  this?.get("state") as String
    address.street =  this?.get("street") as String
    address.township = this?.get("township") as String
    return address
}


fun String.convertToBitMap(): Bitmap?{
    return try{
        val byte = Base64.decode(this, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(byte))
        bitmap
    }catch (e: Exception){
        e.message
        null
    }
}

fun ImageView.loadImage(uri: Uri){
    Glide.with(context)
            .asBitmap()
            .load(uri)
            .centerCrop()
            .into(this)
}


fun toPatient(data: HashMap<String, String>?): Patient? {
    data?.let{
        val patient = Patient()
        patient.image = data.get("image").toString()
        patient.id = data.get("id").toString()
        patient.name = data.get("name").toString()
        patient.email = data.get("email").toString()
//        val value = data.get("question").toString()
//        val gson  = Gson()
//        val ques : MutableList<QuestionVO> =   gson.fromJson(value, Array<QuestionVO>::class.java).toMutableList()
        return patient
    }
    return null



}

fun toDoctor(data : HashMap<String, String>?) : DoctorVO?{

    data?.let{
        val doctor = DoctorVO()
        doctor.name = data.get("name").toString()
        doctor.id = data.get("id").toString()
        doctor.image = data.get("image").toString()
        doctor.biography = data.get("biography").toString()
        doctor.specialities = data.get("specialities").toString()
        return doctor
    }
    return null

}

fun MutableMap<String,Any>?.convertToPatientVO(): Patient {
    val patient= Patient()
    patient.id = this?.get("id") as String
//    patient.address = (this?.get("address") as Address)
    patient.email =  this?.get("email") as String
    patient.phone =  this?.get("phone") as String
    patient.image=  this?.get("image") as String
    patient.name =   this?.get("name") as String
  //patient.question=  this?.get("question") as List<QuestionVO>
//    patient.recent_doctors=  this?.get("recent_doctors") as List<DoctorVO>
    patient.phone=  this?.get("phone") as String

    return patient
}

fun MutableMap<String, Any>?.convertToDoctorVO() : DoctorVO{
    val doctor = DoctorVO()
    doctor.id = this?.get("id") as String
    doctor.name = this?.get("name") as String
    doctor.image = this?.get("image") as String
   doctor.biography = this?.get("biography") as String
    doctor.specialities = this?.get("specialities") as String
//    doctor.phone= this?.get("phone") as String
//    doctor.degrees = this?.get("degrees") as List<String>
//    doctor.device_token = this?.get("device_token") as String
//    doctor.email  =  this?.get("email") as String
     doctor.email = this?.get("email") as String?

    return doctor
}


fun MutableMap<String, Any>?.convertToMedicineVO() : MedicineVO{
    val medicine = MedicineVO()
    medicine.id =  this?.get("id") as String
    medicine.name =  this?.get("name") as String
    medicine.price =  this?.get("price") as Long
//    medicine.note =  this?.get("note") as String?
  //  medicine.price =  this?.get("price") as Long
   // medicine.quantity =  this?.get("quantity") as Long
   // medicine.sub_total =  this?.get("sub_total") as Long
   // medicine.repeat =  this?.get("repeat") as String
   // medicine.routine =  this?.get("routines") as RoutineVO
    return medicine
}

 fun MutableMap<String, Any>?.convertToMedicinesVO() : MedicineVO{
     val medicine = MedicineVO()
     medicine.id =  this?.get("id") as String
     medicine.name =  this?.get("name") as String
     medicine.price =  this?.get("price") as Long
    medicine.note =  this?.get("note") as String?
       medicine.price =  this?.get("price") as Long
      medicine.quantity =  this?.get("quantity") as Long
      medicine.total_day =  this?.get("total_day") as String?
      medicine.tablet =  this?.get("tablet") as String?
      medicine.sub_total =  this?.get("sub_total") as Long
     medicine.routine = this?.get("routines") as String ?
   //   medicine.repeat =  this?.get("repeat") as String
     return medicine
 }

 fun MutableMap<String, Any>?.convertToNote() :  String?{
     var note : String ?= null
      note =   this?.get("consultation_note") as String?
     return  note
 }


 fun MutableMap<String, Any>?.convertToCaseSummary() : CaseSummaryVO{
    val casesummary =  CaseSummaryVO()
    casesummary.id = this?.get("id") as String
    casesummary.questionList = this?.get("questions") as ArrayList<QuestionVO>
    return casesummary
}


fun MutableMap<String, Any>?.convertToConsultationRequest() : ConsultationRequest{
    val consultationRequest = ConsultationRequest()
    consultationRequest.id = this?.get("id") as String
    consultationRequest.consultationId = this?.get("consultation_chat_id") as String?
    consultationRequest.recent_id = this?.get("recent_doctor_id") as String?
    consultationRequest.patient = toPatient((this?.get("patient")  as  HashMap<String, String>))
    consultationRequest.doctor = toDoctor((this?.get("doctor")  as  HashMap<String, String>? ))
   consultationRequest.specialities = this?.get("specialities") as String
   consultationRequest.caseSummary = this?.get("case_summary") as ArrayList<QuestionVO>
    consultationRequest.status = this?.get("status") as String



    return consultationRequest
}


fun  MutableMap<String, Any>?.convertToDeliveryRoutione() : DeliveryRoutine{
    val routine = DeliveryRoutine()
    routine.date = this?.get("date") as String
    routine.time = this?.get("time") as Timestamp
    return  routine
}


 fun randomBDate() : String{
     val bd : MutableList<String> = arrayListOf()
     bd.add("23 Dec 2020")
     bd.add("10 Jan 1998")
     bd.add("15 July 2020")
     bd.add("30 Oct 2020")
     bd.add("27 Apirl 2020")
     return bd.random()
 }

fun MutableMap<String, Any>?.convertToMessageVO() : MessageVO{
    val message = MessageVO()
    message.id = this?.get("id") as String
    message.image =  this?.get("image") as String
    message.sendTime  = this?.get("sender_at") as String ?
    message.senderImage  = this?.get("sender_image") as String
    message.senderId  = this?.get("sender_id") as String
    message.text = this?.get("text") as String?

    return message
}

fun MutableMap<String, Any>?.convertToCheckOut() : CheckOutVO{
    val checkout = CheckOutVO()
    checkout.address = this?.get("address") as Address
    checkout.id = this?.get("id") as String
    checkout.medicine = this?.get("prescription") as List<MedicineVO>
    checkout.patient = this?.get("patient") as Patient
    checkout.total_amount = this?.get("id") as Long
    checkout.delivery_routine  = this?.get("delivery_routine") as DeliveryRoutine

    return checkout
}

fun MutableMap<String, Any>?.convertToSpecialities() : CategoryVO{
    val category = CategoryVO()
    category.id = this?.get("id") as String
    category.image = this?.get("image") as String
    category.name = this?.get("name") as String
    return category
}

fun MutableMap<String, Any>?.convertToConsultationVO() : ConsultationVO{
    val consultation = ConsultationVO()
    consultation.id = this?.get("id") as String
    consultation.special = this?.get("speciality") as String?
    consultation.statue = this?.get("consultation_status") as String
    val ques  : ArrayList<QuestionVO> = arrayListOf()
    val value = this?.get("case_summary") as ArrayList<HashMap<String, Any>>?
    value?.forEach {
        ques.add ( it.convertToQuestionVO())
    }
    consultation.caseSummary = ques
    consultation.doctor = toDoctor(this?.get("doctor") as HashMap<String, String>?)
   consultation.patient =     toPatient(this?.get("patient") as HashMap<String, String>?)
//    consultation.medicine = this?.get("prescriptions") as List<MedicineVO>
//    consultation.message = this?.get("chats") as List<MessageVO>

    return consultation
}

 @RequiresApi(Build.VERSION_CODES.O)
 fun currentDate() : String{
     val dateTime = LocalDateTime.now()
//     println(dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))
//     println(dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)))
     return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT))
 }
