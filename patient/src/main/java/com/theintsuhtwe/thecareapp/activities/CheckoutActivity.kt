package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.CheckOutAdapter
import com.theintsuhtwe.thecareapp.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.thecareapp.fragments.HomeFragment
import com.theintsuhtwe.thecareapp.mvp.presenters.CheckoutPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.CheckoutPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.presenters.NotePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.CheckoutView
import com.theintsuhtwe.thecareapp.mvp.views.NoteView
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.layout_prescription.*

class CheckoutActivity :BaseActivity(), CheckoutView {

    private lateinit var mPresenter : CheckoutPresenter

    private lateinit var mAdapter : CheckOutAdapter

    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"
        fun newIntent(context: Context): Intent {
            return Intent(context, CheckoutActivity::class.java)
        }

        fun newIntentWithId(context: Context, id : String): Intent {
            val intent =  Intent(context, CheckoutActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        setUpPresenter()

        setUpRecyclerView()

        setUpActionListener()

        mPresenter.onUiReady(intent.getStringExtra(CheckoutActivity.PARM_DOCUMENTID).toString(), this)
    }

    private fun setUpPresenter(){
        mPresenter =   getPresenter<CheckoutPresenterImpl, CheckoutView>()

        mAdapter = CheckOutAdapter()
    }

    private fun setUpActionListener(){
        btnPayment.setOnClickListener {
            mPresenter.onTapCheckOut(intent.getStringExtra(CheckoutActivity.PARM_DOCUMENTID).toString())
        }
    }

    private fun setUpRecyclerView(){


        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvMedicine.layoutManager = linearLayoutManager
        rvMedicine.adapter = mAdapter

    }

    override fun displayPrescription(list: List<MedicineVO>) {
        mAdapter.setData(list)
    }

    override fun navigateToHome() {
       startActivity(MainActivity.newIntent(this))
    }

    override fun showErrorMessage(error: String) {
        TODO("Not yet implemented")
    }
}