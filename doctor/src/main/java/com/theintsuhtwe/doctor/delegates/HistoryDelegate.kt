package com.theintsuhtwe.doctor.delegates

interface HistoryDelegate {
    fun onTapNote(id : String)

    fun onTapPrescription(id : String)

    fun onTapChat(id : String, type : String)
}