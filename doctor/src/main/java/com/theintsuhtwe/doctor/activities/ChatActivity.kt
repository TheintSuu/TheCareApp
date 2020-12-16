package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.adapters.*
import com.theintsuhtwe.doctor.mvp.presenters.impls.ChatPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.ChatPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.ChatView
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.fragment_home.*

class ChatActivity : BaseActivity(), ChatView {

    private lateinit var mPresenter : ChatPresenter

    private lateinit  var mChatAdapter : ChatHistoryAdapter

    private lateinit var mAnswerAdapter : QuestionAnswerItemAdapter

    private lateinit var mSpecialAdapter : CaseSummaryAdapter

    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"
        fun newIntent(context: Context): Intent {
            return Intent(context, ChatActivity::class.java)
        }

        fun newIntentWithId(context: Context, id : String): Intent {
            val intent =  Intent(context, ChatActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setUpPresenter()

        setUpRecyclerView()

        setUpActionListener()

        mPresenter.onUiReady(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString(),this)
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<ChatPresenterImpl, ChatView>()

        mChatAdapter =  ChatHistoryAdapter(mPresenter)

        mAnswerAdapter = QuestionAnswerItemAdapter(mPresenter)

        mSpecialAdapter = CaseSummaryAdapter(mPresenter)

    }

    private  fun setUpRecyclerView(){

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager3 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvChatHistory.layoutManager = linearLayoutManager
        rvChatHistory.adapter = mChatAdapter

       rvPatientInfo.layoutManager = linearLayoutManager2
        rvPatientInfo.adapter = mAnswerAdapter

        rvSpecialQuestion.layoutManager = linearLayoutManager3
        rvSpecialQuestion.adapter =  mSpecialAdapter
    }

    private fun setUpActionListener(){
        btnSendMessage.setOnClickListener {
            val text = etMessage.text.toString()
            mPresenter.onTapSend(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString(),
            text, "")
        }
    }

    override fun displayPatientGeneralQuestion(list: ArrayList<QuestionVO>) {
        mAnswerAdapter.setData(list)
    }

    override fun displayPatientSpecialQuestion(list: ArrayList<QuestionVO>) {
        mSpecialAdapter.setData(list)
    }

    override fun displayPatientChat(list: List<MessageVO>) {
        mChatAdapter.setData(list)
    }

    override fun navigateToPrescription() {

    }

    override fun navigateToNote() {

    }

    override fun navigateToCheckout() {

    }

    override fun navigateToSpecialQuestionByDoctor() {

    }

    override fun showErrorMessage(error: String) {

    }
}