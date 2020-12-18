package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.LoginPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.presenters.RegisterPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.RegisterPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.LoginView
import com.theintsuhtwe.thecareapp.mvp.views.RegisterView
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity(), RegisterView {

    private lateinit var mPresenter: RegisterPresenter


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



        mPresenter.onUiReady("hello" , this)
    }

    override fun navigateToToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun showErrorMessage(error: String) {

    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    private fun setUpListener(){
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(this,etEmail.text.toString(), tvPassword.text.toString(), tvName.text.toString())
        }

    }

//    private fun getToken() {
//        FirebaseInstanceId.getInstance().instanceId
//            .addOnCompleteListener(OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.w("Token", "getInstanceId failed", task.exception)
//                    return@OnCompleteListener
//                }
//
//
//               // token = task.result.token
//
//
//
//            })
//    }
}