package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.CategoryVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.RegisterPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.RegisterPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.RegisterView

class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var mPresenter: RegisterPresenter

    private lateinit var token : String

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUpPresenter()

        setUpListener()

        mPresenter.onUiReady(token , this)
    }

    override fun navigateToToLoginScreen() {

    }

    override fun showErrorMessage(error: String) {

    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    private fun setUpListener(){
        mPresenter.onTapRegister(

            "helentheintsuu@gmail.com",
            "hazel$1998",
            "Theint Su",token
        )
    }

    private fun getToken(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Token", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }


                token = task.result.token


                val msg ="token :  $token"
                Log.i("Token ", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()


            })
    }
}