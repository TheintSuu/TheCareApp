//package com.theintsuhtwe.thecareapp.activities
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.theintsuhtwe.shared.activities.BaseActivity
//import com.theintsuhtwe.shared.data.vos.MedicineVO
//import com.theintsuhtwe.thecareapp.R
//import com.theintsuhtwe.thecareapp.adapters.ChatHistoryAdapter
//import com.theintsuhtwe.thecareapp.adapters.PrescriptionAdapter
//import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment
//import com.theintsuhtwe.thecareapp.fragments.DetailPrescriptionFragment
//import com.theintsuhtwe.thecareapp.mvp.presenters.ChatPresenterImpl
//import com.theintsuhtwe.thecareapp.mvp.presenters.PrescriptionPresenter
//import com.theintsuhtwe.thecareapp.mvp.presenters.PrescriptionPresenterImpl
//import com.theintsuhtwe.thecareapp.mvp.views.ChatView
//import com.theintsuhtwe.thecareapp.mvp.views.PrescriptionView
//import kotlinx.android.synthetic.main.activity_chat.*
//import kotlinx.android.synthetic.main.activity_prescription.*
//
//class PrescriptionActivity : BaseActivity(), PrescriptionView {
//
//    private lateinit var mPresenter : PrescriptionPresenter
//    private lateinit var mAdapter : PrescriptionAdapter
//    companion object{
//        const val PARM_DOCUMENTID = "Document ID"
//        const val PARM_DOCUMENTID2 = "Special ID"
//        fun newIntent(context: Context): Intent {
//            return Intent(context,  PrescriptionActivity::class.java)
//        }
//
//        fun newIntentWithId(context: Context, id : String, special : String): Intent {
//            val intent =  Intent(context, PrescriptionActivity::class.java)
//            intent.putExtra(PARM_DOCUMENTID, id)
//            intent.putExtra(PARM_DOCUMENTID2, special)
//            return intent
//        }
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_prescription)
//
//        setUpPresenter()
//
//        setUpRecyclerView()
//
//        setUpListener()
//
//       mPresenter.onUiReady(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID2).toString(), this)
//    }
//
//    private fun setUpPresenter(){
//        mPresenter = getPresenter<PrescriptionPresenterImpl, PrescriptionView>()
//
//        mAdapter =  PrescriptionAdapter(mPresenter)
//    }
//
//    private fun setUpListener(){
//        btnFinishConsultation.setOnClickListener {
//            mPresenter.onTapFinishConsultation(intent.getStringExtra(PrescriptionActivity.PARM_DOCUMENTID).toString())
//        }
//    }
//
//    private fun setUpRecyclerView(){
//        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//        rvMedicine.layoutManager = linearLayoutManager
//        rvMedicine.adapter = mAdapter
//    }
//
//    override fun displayPrescription(list: List<MedicineVO>) {
//        mAdapter.setData(list)
//    }
//
//    override fun navigateToChat(id: String) {
//       startActivity(ChatActivity.newIntentWithId(this, id))
//    }
//
//    override fun showDetailPrescription(id: String, price : Long) {
//        supportFragmentManager?.let {
//            val mDialog  =   DetailPrescriptionFragment.newFragment()
//            val bundle = Bundle()
//            bundle.putString(DetailPrescriptionFragment.BUNDLE_CATEGORY_ID, id)
//            bundle.putLong(DetailPrescriptionFragment.BUNDLE_Price_ID, price)
//
//            mDialog?.arguments = bundle
//            mDialog?.show(
//                it, DetailPrescriptionFragment.BUNDLE_CATEGORY_ID
//            )
//
//        }
//    }
//
//    override fun showErrorMessage(error: String) {
//
//    }
//}