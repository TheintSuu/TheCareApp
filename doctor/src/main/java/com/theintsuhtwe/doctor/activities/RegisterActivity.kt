package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.RegisterPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.RegisterPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.RegisterView
import com.theintsuhtwe.shared.activities.BaseActivity
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

        mPresenter.onUiReady(this)
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
//        mPresenter.onTapRegister(
//            "theintsuuhtwe@gmail.com",
//            "hazel$1998",
//            "Hazel Kuu Kuu",
//            CategoryVO(
//                id="category001",
//                name = "Dental",
//                image=""
//
//            )
//        )

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(etEmail.text.toString(), tvPassword.text.toString())
        }
    }
}