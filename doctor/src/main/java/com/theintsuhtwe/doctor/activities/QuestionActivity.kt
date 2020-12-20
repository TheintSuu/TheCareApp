package com.theintsuhtwe.doctor.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.adapters.QuestionAdapter
import com.theintsuhtwe.doctor.mvp.presenters.impls.QuestionPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.QuestionPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.QuestionView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.models.GeneralQuestionModelImpl
import com.theintsuhtwe.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : BaseActivity() , QuestionView {

    private lateinit var mPresenter: QuestionPresenter
    private lateinit var adapter: QuestionAdapter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, QuestionActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        tex_back.setOnClickListener { onBackPressed() }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<QuestionPresenterImpl , QuestionView>()
        mPresenter.onUiReady(SessionManager.doctor_speciality.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        sendDataToPreviousPage("")
    }

    private fun setUpRecyclerView() {
        rc_general_questions?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = QuestionAdapter(mPresenter)
        rc_general_questions?.adapter = adapter
        rc_general_questions?.setHasFixedSize(false)
    }
    fun sendDataToPreviousPage(questions : String)
    {
        val intent = Intent()
        intent.putExtra("questions" , questions)
        setResult(Activity.RESULT_OK , intent)
        finish()
    }

    override fun displayGeneralQuestions(list: List<QuestionVO>) {
        adapter.setData(list)
    }

    override fun navigateToToChatRoom(questions: String) {
        sendDataToPreviousPage(questions)


    }

    override fun showErrorMessage(error: String) {
        TODO("Not yet implemented")
    }
}