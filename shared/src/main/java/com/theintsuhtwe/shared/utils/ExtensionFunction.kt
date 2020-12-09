package com.theintsuhtwe.shared.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.Timestamp
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.theintsuhtwe.shared.R
import com.theintsuhtwe.shared.data.vos.*
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayInputStream


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
    question.id = this?.get("id") as String
    question.description = this["description"] as String
    question.answer = this["answer"] as String
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


fun MutableMap<String,Any>?.convertToPatientVO(): Patient {
    val patient= Patient()
    patient.id = this?.get("id") as String
    patient.address = (this?.get("address") as Address)
    patient.device_token =  this?.get("device_token") as String
    patient.email =  this?.get("email") as String
    patient.phone =  this?.get("phone") as String
    patient.image=  this?.get("image") as String
    patient.question=  this?.get("question") as List<QuestionVO>
    patient.recent_doctors=  this?.get("recent_doctors") as List<DoctorVO>
    patient.phone=  this?.get("phone") as String

    return patient
}

fun MutableMap<String, Any>?.convertToDoctorVO() : DoctorVO{
    val doctor = DoctorVO()
    doctor.id = this?.get("id") as String
    doctor.name = this?.get("name") as String
   doctor.biography = this?.get("biography") as String
    doctor.specialities = this?.get("specialities") as String
//    doctor.phone= this?.get("phone") as String
//    doctor.degrees = this?.get("degrees") as List<String>
//    doctor.device_token = this?.get("device_token") as String
//    doctor.email  =  this?.get("email") as String
     doctor.image = this?.get("image") as String

    return doctor
}


fun MutableMap<String, Any>?.convertToMedicineVO() : MedicineVO{
    val medicine = MedicineVO()
    medicine.id =  this?.get("id") as String
    medicine.name =  this?.get("name") as String
    medicine.note =  this?.get("note") as String
    medicine.price =  this?.get("price") as Long
    medicine.quantity =  this?.get("quantity") as Long
    medicine.sub_total =  this?.get("sub_total") as Long
    medicine.repeat =  this?.get("repeat") as String
    medicine.routine =  this?.get("routines") as RoutineVO
    return medicine
}

fun MutableMap<String, Any>?.convertToCaseSummary() : CaseSummaryVO{
    val casesummary =  CaseSummaryVO()
    casesummary.id = this?.get("id") as String
    casesummary.questionList = this?.get("questions") as List<QuestionVO>
    return casesummary
}


fun MutableMap<String, Any>?.convertToConsultationRequest() : ConsultationRequest{
    val consultationRequest = ConsultationRequest()
    consultationRequest.id = this?.get("id") as String
    consultationRequest.patient = this?.get("patient") as  Patient
    consultationRequest.doctor = this?.get("doctor") as DoctorVO
    consultationRequest.caseSummary = this?.get("case_summary") as CaseSummaryVO
    return consultationRequest
}


fun  MutableMap<String, Any>?.convertToDeliveryRoutione() : DeliveryRoutine{
    val routine = DeliveryRoutine()
    routine.date = this?.get("date") as String
    routine.time = this?.get("time") as Timestamp
    return  routine
}


fun MutableMap<String, Any>?.convertToMessageVO() : MessageVO{
    val message = MessageVO()
    message.id = this?.get("id") as String
    message.image =  this?.get("image") as String
    message.sendTime  = this?.get("sendAt") as String
    message.text = this?.get("text") as String
    message.senderType = this?.get("senderType") as SenderType
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
    consultation.caseSummary = this?.get("id") as CaseSummaryVO
    consultation.doctor = this?.get("doctor") as DoctorVO
    consultation.patient =     this?.get("patient") as Patient
    consultation.medicine = this?.get("prescriptions") as List<MedicineVO>
    consultation.message = this?.get("chats") as List<MessageVO>

    return consultation
}
