package com.theintsuhtwe.thecareapp.delegates

interface SpecialitiesItemDelegate {
    fun onTapSpecialities(name: String)

    fun onTapQuestion(descption : String, answer : String)

    fun onTapRecentDoctor(id: String, doctorId: String)

//    fun onTapStartConsultation(name: String)
//
//    fun onTapAccept(id : String)

}