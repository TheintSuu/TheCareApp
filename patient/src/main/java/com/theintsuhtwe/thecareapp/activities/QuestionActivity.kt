package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.thecareapp.adapters.QuestionItemAdapter
import com.theintsuhtwe.thecareapp.adapters.RecentDoctorItemDoctorAdapter
import com.theintsuhtwe.thecareapp.adapters.SpecialityItemAdapter
import com.theintsuhtwe.thecareapp.mvp.presenters.CaseSummaryQuestionPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.CaseSummaryQuestionPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.CaseSumaryQuestionView
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.fragment_home.*

class QuestionActivity : BaseActivity(), CaseSumaryQuestionView {

    private lateinit var mPresenter: CaseSummaryQuestionPresenter

    private lateinit var mAdapter : QuestionAnswerItemAdapter

    private var type = "once"

    private var queList : MutableList<QuestionVO> = arrayListOf()

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, QuestionActivity::class.java)
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

        mPresenter.onUiReady("patient000", this)
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

                    val ques : MutableList<QuestionVO> = arrayListOf()
                    val data = QuestionVO(description = getString(R.string.wei), answer = ed_weight.text.toString())
                    val data2 = QuestionVO(description = getString(R.string.blood_pres), answer = ed_bloodpressure.text.toString())
                    val data3 = QuestionVO(description = getString(R.string.height) , answer = ed_height.text.toString())
                    val data4 = QuestionVO(description = getString(R.string.allegric_comment), answer = ed_comment.text.toString())
                    val data5 = QuestionVO(description = getString(R.string.bl_type), answer = bloodtype_spinner.selectedItem.toString())
                    val data6 = QuestionVO(description = getString(R.string.Birth), answer = day_spinner.selectedItem.toString()+"/"+ month_spinner.selectedItem.toString()+"/"+year_spinner.selectedItem.toString())
                    ques.add(data)
                    ques.add(data2)
                    ques.add(data3)
                    ques.add(data4)
                    ques.add(data5)
                    ques.add(data6)
                    queList.addAll(ques)
                    mPresenter.onTapNext("patient000", type, "Dental", ques)

            }
            else -> {
                    queList.clear()
                    val ques : MutableList<QuestionVO> = arrayListOf()
                    val data = QuestionVO(description = getString(R.string.wei), answer = ed_always_weight.text.toString())
                    val data2 = QuestionVO(description = getString(R.string.blood_pres), answer = ed_alwyas_bloodpressure.text.toString())
                    ques.add(data)
                    ques.add(data2)
                    queList.addAll(ques)
                    mPresenter.onTapNext("patient000", type, "Dental", ques)
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