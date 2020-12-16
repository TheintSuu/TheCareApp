package com.theintsuhtwe.doctor

import android.app.Application
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.data.models.*

class DoctorApp : Application()
{
    override fun onCreate() {
        super.onCreate()
        PatientModelImpl.initDatabase(applicationContext)
        DoctorModelImpl.initDatabase(applicationContext)
        SpecialitiesModelImpl.initDatabase(applicationContext)
        CheckoutModelImpl.initDatabase(applicationContext)
        GeneralQuestionModelImpl.initDatabase(applicationContext)
        ConsultationModelImpl.initDatabase(applicationContext)
        SessionManager.init(applicationContext)

    }
}