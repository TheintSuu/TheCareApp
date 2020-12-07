package com.theintsuhtwe.thecareapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.MainPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.MainPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.MainView

class MainActivity :  BaseActivity(), MainView {

    private lateinit var mPresenter: MainPresenter


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        setUpRecyclerView()

        setUpListener()

        getToken()



        mPresenter.onUiReady(this)
    }

    @SuppressLint("LongLogTag")
    override fun displaySpecialities(category: List<CategoryVO>) {
        Log.d("Show data from Network Layer", category.toMutableList().toString())
    }

    @SuppressLint("LongLogTag")
    override fun displayDoctorBySpecialities(doctor: List<DoctorVO>) {
        Log.d("Broadcast Request to Suitable Doctor", doctor.toMutableList().toString())
        mPresenter.onTapConsultationRequest(CaseSummaryVO(), Patient(), CategoryVO())

    }

    override fun displayConsultationConfirm(doctor: DoctorVO) {

    }

    override fun displayQuestions(qustions: List<QuestionVO>) {

    }

    override fun displayRecentDoctorList(doctors: List<DoctorVO>) {

    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpRecyclerView(){

    }

    private fun setUpListener(){
        mPresenter.onTapCategory("category001")

    }

    private fun getToken(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Token", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg ="token :  $token"
                Log.i("Token ", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()


            })
    }
}