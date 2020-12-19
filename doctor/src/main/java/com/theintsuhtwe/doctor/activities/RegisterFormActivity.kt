package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.LoginPresenterImpl
import com.theintsuhtwe.doctor.mvp.presenters.impls.RegisterFormPresenterImpl
import com.theintsuhtwe.doctor.mvp.presenters.impls.RegisterPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.RegisterPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.LoginView
import com.theintsuhtwe.doctor.mvp.views.RegisterFormView
import com.theintsuhtwe.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_register_form.*

class RegisterFormActivity : BaseActivity(), RegisterFormView {
    private lateinit var mPresenter: RegisterFormPresenterImpl

    private var special = ""

    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"


        fun newIntentWithId(context: Context, email : String): Intent {
            val intent =  Intent(context, RegisterFormActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, email)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)
        setUpPresenter()

        setUpActionListeners()

        mPresenter.onUiReady( )
    }

    private fun setUpActionListeners() {
        etSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                special = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

       btnSave.setOnClickListener(){
           mPresenter.onTapSave(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString(),
                edName.text.toString(),
               etPhone.text.toString(),
               special,
               etBD.text.toString(),
               etDegrees.text.toString(),
               etBiography.text.toString()
               )
       }

        toolbar.setOnClickListener {
            onBackPressed()
        }


//        btnRegister.setOnClickListener {
//            mPresenter.onTapRegister()
//        }
    }



    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterFormPresenterImpl, RegisterFormView>()
    }

    override fun navigateToHome() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun showErrorMessage(error: String) {

    }

}