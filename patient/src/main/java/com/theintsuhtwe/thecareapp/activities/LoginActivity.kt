package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.LoginPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.LoginPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.LoginView
import com.theintsuhtwe.thecareapp.utils.SessionManager

class LoginActivity : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

     //private lateinit var  token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getToken()

        setUpPresenter()

        setUpActionListeners()



        mPresenter.onUiReady( "token", this)
    }

    private fun setUpActionListeners() {

        mPresenter.onTapLogin(this,"helentheintsuu@gmail.com","hazel$1998")


//        btnRegister.setOnClickListener {
//            mPresenter.onTapRegister()
//        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    override fun navigateToHomeScreen(patientVO: Patient) {
        savePatientInfo(patientVO)
        startActivity(MainActivity.newIntent(this))

    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }

     override fun showErrorMessage(error: String) {

     }

     private fun getToken(){
         FirebaseInstanceId.getInstance().instanceId
             .addOnCompleteListener(OnCompleteListener { task ->
                 if (!task.isSuccessful) {
                     Log.w("Token", "getInstanceId failed", task.exception)
                     return@OnCompleteListener
                 }


                 // token = task.result.token


                 //val msg ="token :  $token"
                 //Log.i("Token ", msg)
                 //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()


             })
     }

     private fun savePatientInfo(patientVO: Patient){
         patientVO.id?.let {
             SessionManager.patient_name = patientVO.name
             SessionManager.patient_id = patientVO.id
             SessionManager.patient_device_token = patientVO.device_token
             SessionManager.patient_email = patientVO.email
             SessionManager.patient_photo = patientVO.image.toString()
             patientVO.question?.forEach {
                 when(it.description){
                     "မွေးနေ့" -> {
                         SessionManager.patient_dateOfBirth = it.answer
                     }
                     "အရပ်" -> {
                         SessionManager.patient_height = it.answer
                     }
                     "မတည့်သော ဆေးအမျိုးအစား" -> {
                         SessionManager.patient_comment = it.answer
                     }
                     "ပေါင်ချိန်" -> {
                         SessionManager.patient_weight = it.answer
                     }
                     "သွေးပေါင်ချိန်" -> {
                         SessionManager.patient_bloodPressure = it.answer
                     }
                     "သွေးအမျိုးအစား"  -> {
                         SessionManager.patient_bloodType = it.answer
                     }

                 }
             }
         }


     }
}