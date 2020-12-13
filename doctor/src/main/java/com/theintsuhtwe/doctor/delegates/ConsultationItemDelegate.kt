package com.theintsuhtwe.doctor.delegates

interface ConsultationItemDelegate {
    fun onTapRequest(name: String)

    fun onTapAccept(id : String)

    fun onTapConsultationHistory(name : String)
}