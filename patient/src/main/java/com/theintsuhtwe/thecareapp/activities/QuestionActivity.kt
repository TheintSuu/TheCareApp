package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.thecareapp.mvp.presenters.CaseSummaryQuestionPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.CaseSummaryQuestionPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import kotlin.collections.ArrayList

class QuestionActivity : BaseActivity(), CaseSumaryQuestionView {

    private lateinit var mPresenter: CaseSummaryQuestionPresenter

    private lateinit var mAdapter : QuestionAnswerItemAdapter

    private var type = "once"

    private var queList : ArrayList<QuestionVO> = arrayListOf()

    companion object {
        const val PARM_DOCUMENTID = "Document ID"

        fun newIntent(context: Context, id : String): Intent {
            val intent =  Intent(context, QuestionActivity::class.java)
            intent.putExtra(QuestionActivity.PARM_DOCUMENTID, id)
            return intent
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_question)

        setUpPresenter()

        setUpListener()

        setUpRecyclerView()

        hideOnceTimeAnswer()

        hideAlwawysQuestion()

        hideSpecialQuestion()

        mPresenter.onUiReady(SessionManager.patient_id.toString(), this)
    }


    private fun setUpPresenter(){
        mPresenter = getPresenter<CaseSummaryQuestionPresenterImpl ,CaseSumaryQuestionView>()


    }

    private fun setUpRecyclerView(){
        mAdapter = QuestionAnswerItemAdapter(mPresenter)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvQuestion.layoutManager = linearLayoutManager
        rvQuestion.adapter = mAdapter
    }

    private fun setUpListener(){
        btn_confirm.setOnClickListener {
        when(type){
            getString(R.string.once) -> {

                    val ques : ArrayList<QuestionVO> = arrayListOf()
                    val data = QuestionVO(id = UUID.randomUUID().toString(),description = getString(R.string.wei), answer = ed_weight.text.toString())
                    val data2 = QuestionVO(id = UUID.randomUUID().toString(),description = getString(R.string.blood_pres), answer = ed_bloodpressure.text.toString())
                    val data3 = QuestionVO(id = UUID.randomUUID().toString(),description = getString(R.string.height) , answer = ed_height.text.toString())
                    val data4 = QuestionVO(id = UUID.randomUUID().toString(),description = getString(R.string.allegric_comment), answer = etNote.text.toString())
                    val data5 = QuestionVO(id = UUID.randomUUID().toString(),description = getString(R.string.bl_type), answer = bloodtype_spinner.selectedItem.toString())

                    ques.add(data)
                    ques.add(data2)
                    ques.add(data3)
                    ques.add(data4)
                    ques.add(data5)
                    queList.addAll(ques)
                    mPresenter.onTapNextOnce(SessionManager.patient_id.toString(),  type, edName.text.toString(), etBD.text.toString(), etPhone.text.toString(),  intent.getStringExtra(QuestionActivity.PARM_DOCUMENTID).toString(),  ques)

            }
            else -> {
                    queList.clear()
                    val ques : ArrayList<QuestionVO> = arrayListOf()
                    val data = QuestionVO(id = UUID.randomUUID().toString(), description = getString(R.string.wei), answer = ed_always_weight.text.toString())
                    val data2 = QuestionVO(id = UUID.randomUUID().toString(),description = getString(R.string.blood_pres), answer = ed_alwyas_bloodpressure.text.toString())
                    ques.add(data)
                    ques.add(data2)
                    queList.addAll(ques)
                    mPresenter.onTapNext(SessionManager.patient_id.toString(), type, intent.getStringExtra(QuestionActivity.PARM_DOCUMENTID).toString(), ques)
                }
            }
        }


    }

    override fun displayOneTimeQuestion(ques: List<QuestionVO>) {

    }

    override fun displayOneTimeAnswer(ques: List<QuestionVO>) {
        when(ques.isEmpty()){
            true -> {
                hideOnceTimeAnswer()
                showOnceTimeQuestion()
                hideAlwawysQuestion()
            }
            else -> {
                showOnceTimeAnswer(ques)
                hideOnceTimeQuestion()
                showAlwaysQuestion()
            }
        }
    }

    override fun showAlwaysQuestion(id: String, req : String) {


        always.visibility = View.VISIBLE
    }

    override fun navigaeToSpecialQuestion(id: String, req : String) {
        startActivity(SpecialQuestionActivity.newIntent(this, req))
    }



    override fun showErrorMessage(error: String) {

    }

    private fun showOnceTimeAnswer(ques: List<QuestionVO>){
        mAdapter.setData(ques)
        type = "always"
        queList.clear()
        queList.addAll(ques)
        answer.visibility = View.VISIBLE
    }

    private fun hideOnceTimeAnswer(){
        answer.visibility = View.GONE
    }

    private fun showOnceTimeQuestion(){
        oneTime.visibility = View.VISIBLE
    }

    private fun hideOnceTimeQuestion(){
        oneTime.visibility = View.GONE
    }

    private fun showAlwaysQuestion(){
        always.visibility = View.VISIBLE
    }

    private fun hideAlwawysQuestion(){
        always.visibility = View.GONE
    }

    private fun hideSpecialQuestion(){

    }

    private fun showSpecialQuestion(){

    }

}