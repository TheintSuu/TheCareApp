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
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.LoginPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.LoginPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.LoginView

 class LoginActivity : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

     private lateinit var  token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpPresenter()

        setUpActionListeners()

        getToken()

        mPresenter.onUiReady( token, this)
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

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
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