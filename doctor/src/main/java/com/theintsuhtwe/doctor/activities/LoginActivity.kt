package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.LoginPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.LoginPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.LoginView
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.DoctorVO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpPresenter()

        setUpActionListeners()

        mPresenter.onUiReady( this)
    }

    private fun setUpActionListeners() {

//            mPresenter.onTapLogin(this,"helentheintsuu@gmail.com","hazel$1998")
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }

        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(this, etEmail.text.toString(), tvPassword.text.toString())
        }


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

    override fun navigateToRegisterForm(email: String) {
        startActivity(RegisterFormActivity.newIntentWithId(this, email))
    }

    override fun showErrorMessage(error: String) {

    }
}