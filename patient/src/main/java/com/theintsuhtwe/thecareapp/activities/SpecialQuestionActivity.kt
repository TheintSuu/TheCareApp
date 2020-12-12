package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.thecareapp.adapters.QuestionItemAdapter
import com.theintsuhtwe.thecareapp.mvp.presenters.*
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView
import com.theintsuhtwe.thecareapp.mvp.views.CaseSummaryView
import com.theintsuhtwe.thecareapp.mvp.views.SpecialQuestionView
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_special_question.*

class SpecialQuestionActivity :  BaseActivity() , SpecialQuestionView{

    private lateinit var mPresenter: SpecialQuestionPresenter

    private lateinit var mAdapter : QuestionItemAdapter

    private var mQueList : MutableList<QuestionVO> = arrayListOf()


    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        fun newIntent(context: Context): Intent {
            return Intent(context, SpecialQuestionActivity::class.java)
        }

        fun newIntent(context: Context, id : String): Intent {
            val intent =  Intent(context, SpecialQuestionActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_question)

        setUpPresenter()

        setUpRecyclerView()

        setUpListener()

        mPresenter.onUiReady(intent.getStringExtra(PARM_DOCUMENTID).toString(), this)
    }

    private fun setUpListener(){
        btnConfirm.setOnClickListener{
            mPresenter.onTapContinue(mQueList)

        }
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<SpecialQuestionPresenterImpl, SpecialQuestionView>()
    }

    private fun setUpRecyclerView(){
        mAdapter = QuestionItemAdapter(mPresenter)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvSpecial.layoutManager = linearLayoutManager

        rvSpecial.adapter = mAdapter
    }

    override fun displayQuestionBySpeciality(ques: List<QuestionVO>) {
        mAdapter.setData(ques)
    }

    override fun navigateToCaseSummary(summaryId: String) {

    }

    override fun addQuestionToCaseSummary(ques: QuestionVO) {
       mQueList.add(ques)
    }

    override fun showErrorMessage(error: String) {

    }
}