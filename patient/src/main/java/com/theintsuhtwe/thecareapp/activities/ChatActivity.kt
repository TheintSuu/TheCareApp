package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.CaseSummaryAdapter
import com.theintsuhtwe.thecareapp.adapters.ChatHistoryAdapter
import com.theintsuhtwe.thecareapp.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.thecareapp.mvp.presenters.ChatPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.ChatPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.ChatView
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : BaseActivity(), ChatView {

    private lateinit var mPresenter : ChatPresenter

    private lateinit  var mChatAdapter : ChatHistoryAdapter

    private lateinit var mAnswerAdapter : QuestionAnswerItemAdapter

    private lateinit var mSpecialAdapter : CaseSummaryAdapter

    private var special = ""

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
            etMessage.setText("")
            text?.isNotBlank().let {
                mPresenter.onTapSend(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString(),
                    text, "")
            }


        }

        btnRecordNote.setOnClickListener {
            startActivity(NoteActivity.newIntentWithId(this, intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString() ))
        }

        btnPrescription.setOnClickListener {
            mPresenter.onTapPrescription(special)
        }
    }

    override fun displayPatientGeneralQuestion(list: ArrayList<QuestionVO>) {
        mAnswerAdapter.setData(list)
    }

    override fun displayPatientSpecialQuestion(list: ArrayList<QuestionVO>) {

        mSpecialAdapter.setData(list.take(2))
    }

    override fun displayPatientChat(list: List<MessageVO>) {
        mChatAdapter.setData(list)
    }

    override fun navigateToPrescription(special : String) {
        startActivity(PrescriptionActivity.newIntentWithId(this, intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString(), special ))
    }

    override fun navigateToNote() {

    }

    override fun navigateToCheckout() {

    }

    override fun showSpeciality(id: String) {
        special = id
    }

    override fun navigateToSpecialQuestionByDoctor() {

    }

    override fun showErrorMessage(error: String) {

    }
}