package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.CaseSummaryAdapter
import com.theintsuhtwe.thecareapp.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.thecareapp.fragments.HomeFragment
import com.theintsuhtwe.thecareapp.mvp.presenters.CaseSummaryPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.CaseSummaryPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.activity_case_summary.*


class CaseSummaryActivity : BaseActivity(), CaseSummaryView {

    private lateinit var mPresenter: CaseSummaryPresenter

    private lateinit var mAdapter : CaseSummaryAdapter

    private var caseSummaryVO : ArrayList<QuestionVO> = arrayListOf()

    private lateinit var mPatientAdapter : QuestionAnswerItemAdapter

    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"
        fun newIntent(context: Context): Intent {
            return Intent(context, CaseSummaryActivity::class.java)
        }

        fun newIntent(context: Context, id : String, special : String): Intent {
            val intent =  Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            intent.putExtra(PARM_DOCUMENTID2, special)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_summary)

        setUpPresenter()

        setUpRecyclerView()

        setUpListener()

        mPresenter.onUiReady(intent.getStringExtra(CaseSummaryActivity.PARM_DOCUMENTID).toString(), this)

    }

    private fun setUpListener(){
        btnConfirm.setOnClickListener{
            mPresenter.onTapSendConsultationRequest(intent.getStringExtra(CaseSummaryActivity.PARM_DOCUMENTID2).toString(),caseSummaryVO, this)
        }
        toolbar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<CaseSummaryPresenterImpl, CaseSummaryView>()
    }

    private fun setUpRecyclerView(){
        mAdapter =  CaseSummaryAdapter(mPresenter)
        mPatientAdapter =  QuestionAnswerItemAdapter(mPresenter)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

       rvPatientInfo.layoutManager = linearLayoutManager

        rvPatientInfo.adapter =mPatientAdapter

        rvSpecialQuestionList.layoutManager = linearLayoutManager2

        rvSpecialQuestionList.adapter = mAdapter
    }

    override fun displayGeneralQuestion(que: ArrayList<QuestionVO>) {
        mPatientAdapter.setData(que)
    }

    override fun displayCaseSummary(que:ArrayList<QuestionVO>) {
        caseSummaryVO = que
        mAdapter.setData(que)
    }

    override fun navigateToHome(requestId: String) {
            SessionManager.request_id = requestId
            startActivity(MainActivity.newIntent(this))
//        supportFragmentManager.beginTransaction().show(HomeFragment.newInstance(requestId, "")).commit()
    }

    override fun showErrorMessage(error: String) {

    }
}