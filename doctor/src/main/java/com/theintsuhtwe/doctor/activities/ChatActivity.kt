package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.adapters.CaseSummaryAdapter
import com.theintsuhtwe.doctor.adapters.ChatHistoryAdapter
import com.theintsuhtwe.doctor.adapters.ChatPrescriptionAdapter
import com.theintsuhtwe.doctor.adapters.QuestionAnswerItemAdapter
import com.theintsuhtwe.doctor.mvp.presenters.impls.ChatPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.ChatPresenterImpl
import com.theintsuhtwe.doctor.mvp.views.ChatView
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.MessageVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.layout_chat_prescription.*


class ChatActivity : BaseActivity(), ChatView {

    private lateinit var mPresenter : ChatPresenter

    private lateinit  var mChatAdapter : ChatHistoryAdapter

    private lateinit var mAnswerAdapter : QuestionAnswerItemAdapter

    private lateinit var mSpecialAdapter : CaseSummaryAdapter

    private lateinit var mPrescriptionAdapter : ChatPrescriptionAdapter

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

        fun newIntentWithType(context: Context, id : String, type : String): Intent {
            val intent =  Intent(context, ChatActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            intent.putExtra(PARM_DOCUMENTID2, type)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        setUpPresenter()

        setUpRecyclerView()

        hidePrescription()

        showSendMessage()

        setUpActionListener()

        hideView()

        bindData()

        mPresenter.onUiReady(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString(),this)
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<ChatPresenterImpl, ChatView>()

        mChatAdapter =  ChatHistoryAdapter(mPresenter)

        mAnswerAdapter = QuestionAnswerItemAdapter(mPresenter)

        mSpecialAdapter = CaseSummaryAdapter(mPresenter)

        mPrescriptionAdapter = ChatPrescriptionAdapter()

    }

    private  fun setUpRecyclerView(){

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager3 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val linearLayoutManager4 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvChatHistory.layoutManager = linearLayoutManager
        rvChatHistory.adapter = mChatAdapter

        rvPatientInfo.layoutManager = linearLayoutManager2
        rvPatientInfo.adapter = mAnswerAdapter

        rvSpecialQuestion.layoutManager = linearLayoutManager3
        rvSpecialQuestion.adapter =  mSpecialAdapter

        rvPrescriptionMedicine.layoutManager = linearLayoutManager4
        rvPrescriptionMedicine.adapter = mPrescriptionAdapter
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

        tvName.setOnClickListener {
           onBackPressed()
        }



        if(intent.getStringExtra(ChatActivity.PARM_DOCUMENTID2).toString() == getString(R.string.finish))  hideSendMessage()  else showSendMessage()


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
        val id = intent.getStringExtra(ChatActivity.PARM_DOCUMENTID).toString()
        startActivity(PrescriptionActivity.newIntentWithId(this, id , special ))
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

    override fun displayPrescription(list: List<MedicineVO>) {
        when(list.size>0){
            true -> {
                showPrescription()
               mPrescriptionAdapter.setData(list)
              // startActivity(MainActivity.newIntent(this))
            }
            else -> {
                hidePrescription()
            }
        }
    }



    override fun navigateToCheckOut(id: String) {

    }

    override fun showErrorMessage(error: String) {

    }

    private fun showPrescription(){
        layoutChatPrescription.visibility = View.VISIBLE
    }

    private fun hidePrescription(){
        layoutChatPrescription.visibility = View.GONE
    }

    private fun showSendMessage(){
        layoutSendHide.visibility = View.VISIBLE
    }

    private fun hideSendMessage(){

        layoutSendHide.visibility = View.GONE

    }

    private fun bindData(){
        Glide.with(this)
                .load(SessionManager.doctor_image.toString())
                .into(userprofile)

        tvName.text = SessionManager.doctor_name
    }

    private fun hideView(){

    }

}