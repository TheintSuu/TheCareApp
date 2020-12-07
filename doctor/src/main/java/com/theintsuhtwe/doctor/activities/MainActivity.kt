package com.theintsuhtwe.doctor.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.MainPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.MainPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.MainView
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO

class MainActivity : BaseActivity(), MainView {


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

        setUpActionListeners()

        mPresenter.onUiReady("Dental", this)
    }



    private fun setUpPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setUpActionListeners() {


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

    @SuppressLint("LongLogTag")
    override fun displaySpecialQuestionByCateogry(category: List<QuestionVO>) {
        Log.d("Show Special Question from Network Layer", category.toMutableList().toString())
    }

    @SuppressLint("LongLogTag")
    override fun displayGeneralQuestionByCateogry(category: List<QuestionVO>) {
        Log.d("Prepare General Question from Network Layer", category.toMutableList().toString())
    }

    @SuppressLint("LongLogTag")
    override fun displayMedicineQuestionByCateogry(category: List<MedicineVO>) {
        Log.d("Prepare Medicine from Network Layer", category.toMutableList().toString())
    }


}