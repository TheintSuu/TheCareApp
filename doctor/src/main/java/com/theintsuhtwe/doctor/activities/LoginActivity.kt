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

            mPresenter.onTapLogin(this,"helentheintsuu@gmail.com","hazel$1998")


//        btnRegister.setOnClickListener {
//            mPresenter.onTapRegister()
//        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    override fun navigateToHomeScreen(doctorVO: DoctorVO) {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}