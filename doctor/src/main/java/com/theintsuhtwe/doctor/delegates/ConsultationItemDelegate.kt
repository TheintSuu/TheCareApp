package com.theintsuhtwe.doctor.delegates

import com.theintsuhtwe.shared.data.vos.ConsultationRequest

interface ConsultationItemDelegate {
    fun onTapRequest(name: String)

    fun onTapAccept(id : String)

    fun onTapCancel(id : ConsultationRequest)


}