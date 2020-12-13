package com.theintsuhtwe.thecareapp

import android.app.Application
import com.theintsuhtwe.shared.data.models.*
import com.theintsuhtwe.thecareapp.utils.SessionManager

class Patient : Application()
{
    override fun onCreate() {
        super.onCreate()
        PatientModelImpl.initDatabase(applicationContext)
        SpecialitiesModelImpl.initDatabase(applicationContext)
        CheckoutModelImpl.initDatabase(applicationContext)
        GeneralQuestionModelImpl.initDatabase(applicationContext)
        ConsultationModelImpl.initDatabase(applicationContext)
        SessionManager.init(applicationContext)

    }
}