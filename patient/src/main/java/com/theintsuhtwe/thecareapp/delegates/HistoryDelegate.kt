package com.theintsuhtwe.thecareapp.delegates

interface HistoryDelegate {
    fun onTapNote(id : String)

    fun onTapPrescription(id : String)

    fun onTapChat(id : String)
}