package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.CheckOutAdapter
import com.theintsuhtwe.thecareapp.mvp.presenters.BeforeCheckOutPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.BeforeCheckOutPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.CheckOutDisplayView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.activity_before_check_out.*


class BeforeCheckOut : BaseActivity(), CheckOutDisplayView {

    private lateinit var mPresenter: BeforeCheckOutPresenter

    private lateinit var mAdapter: CheckOutAdapter

    companion object {
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"
        fun newIntent(context: Context): Intent {
            return Intent(context,  BeforeCheckOut::class.java)
        }

        fun newIntentWithId(context: Context, id: String): Intent {
            val intent = Intent(context, BeforeCheckOut::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_before_check_out)
        setUpPresenter()

        setUpRecyclerView()

        setUpActionListener()

        bindData()

        mPresenter.onUiReady(intent.getStringExtra(CheckoutActivity.PARM_DOCUMENTID).toString(), this)
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<BeforeCheckOutPresenterImpl, CheckOutDisplayView>()

        mAdapter = CheckOutAdapter()


    }

    private fun setUpActionListener() {
        btn_order.setOnClickListener {
            mPresenter.onTapCheckOut(intent.getStringExtra(CheckoutActivity.PARM_DOCUMENTID).toString())
        }


    }

    private fun setUpRecyclerView() {


        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        prescription_rct.layoutManager = linearLayoutManager
        prescription_rct.adapter = mAdapter

    }





    override fun displayPrescriptionView(list: List<MedicineVO>) {
        mAdapter.setData(list)
        var total: Long = 1
        list.forEach {
            it?.sub_total.let {
                if (it != null) {
                    total += it
                }
            }

        }
        total_amount.text = total.toString()
       mAdapter.setData(list)
    }

    override fun navigateToCheckOut(id: String) {
       finish()
        startActivity(CheckoutActivity.newIntentWithId(this, id))
    }

    override fun showErrorMessage(error: String) {

    }

    private fun bindData(){
        tvAddress.text = SessionManager.patient_address.toString()
    }

}