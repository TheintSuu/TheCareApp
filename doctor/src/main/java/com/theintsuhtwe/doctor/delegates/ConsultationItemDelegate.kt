package com.theintsuhtwe.doctor.delegates

import com.theintsuhtwe.shared.data.vos.ConsultationRequest

interface ConsultationItemDelegate {
    fun onTapRequest(name: String)

    fun onTapAccept(requesst: ConsultationRequest)

    fun onTapCancel(id : ConsultationRequest)


}