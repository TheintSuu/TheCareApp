package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.adapters.PrescriptionAdapter
import com.theintsuhtwe.doctor.fragments.DetailPrescriptionFragment
import com.theintsuhtwe.doctor.mvp.presenters.impls.PrescriptionPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.PrescriptionPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.PrescriptionView
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.MedicineVO
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.activity_prescription.*

class PrescriptionActivity : BaseActivity(), PrescriptionView {

    private lateinit var mPresenter : PrescriptionPresenter
    private lateinit var mAdapter : PrescriptionAdapter

    private var mPrescription : MutableList<MedicineVO> = arrayListOf()
    private var mPrescriptionFilter : MutableList<MedicineVO> = arrayListOf()

    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"
        fun newIntent(context: Context): Intent {
            return Intent(context,  PrescriptionActivity::class.java)
        }

        fun newIntentWithId(context: Context, id : String, special : String): Intent {
            val intent =  Intent(context, PrescriptionActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            intent.putExtra(PARM_DOCUMENTID2, special)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_prescription)

        setUpPresenter()

        setUpRecyclerView()

        setUpListener()

       mPresenter.onUiReady(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID2).toString(), this)
    }


    private fun setUpPresenter(){
        mPresenter = getPresenter<PrescriptionPresenterImpl, PrescriptionView>()

        mAdapter =  PrescriptionAdapter(mPresenter)
    }

    private fun setUpListener(){
        btnFinishConsultation.setOnClickListener {
            mPresenter.onTapFinishConsultation(intent.getStringExtra(PrescriptionActivity.PARM_DOCUMENTID).toString())
        }

        etConsultationNote.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val medicine = MedicineVO()
                medicine.name = s.toString()

                val filter: List<MedicineVO> = mPrescriptionFilter.filter {
                  med ->
                    med.name.contains(medicine.name.toString())
                }
                if(filter.isNotEmpty()) mAdapter.setData(filter)
            }
        })





    }

    private fun setUpRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvMedicine.layoutManager = linearLayoutManager
        rvMedicine.adapter = mAdapter
    }

    override fun displayPrescription(list: List<MedicineVO>) {
        mPrescription.clear()
        mPrescription.addAll(list)
        mPrescriptionFilter.clear()
        mPrescriptionFilter.addAll(list)
        mAdapter.setData(list)
    }

    override fun navigateToHome(id: String) {
       startActivity(MainActivity.newIntent(this))
    }

    override fun showDetailPrescription(id: String, price : Long) {
        supportFragmentManager?.let {
            val mDialog  =   DetailPrescriptionFragment.newFragment()
            val bundle = Bundle()
            bundle.putString(DetailPrescriptionFragment.BUNDLE_CATEGORY_ID, id)
            bundle.putLong(DetailPrescriptionFragment.BUNDLE_Price_ID, price)

            mDialog?.arguments = bundle
            mDialog?.show(
                it, DetailPrescriptionFragment.BUNDLE_CATEGORY_ID
            )

        }
    }

    override fun showErrorMessage(error: String) {

    }
}