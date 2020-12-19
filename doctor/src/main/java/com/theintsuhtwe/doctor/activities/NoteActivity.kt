package com.theintsuhtwe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.annotation.RequiresApi
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.NotePresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.NotePresenterImpl
import com.theintsuhtwe.doctor.mvp.views.NoteView
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.shared.utils.currentDate
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity(), NoteView {
    private lateinit var mPresenter : NotePresenter
    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        const val PARM_DOCUMENTID2 = "Special ID"
        fun newIntent(context: Context): Intent {
            return Intent(context,  NoteActivity::class.java)
        }

        fun newIntentWithId(context: Context, id : String): Intent {
            val intent =  Intent(context,NoteActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)

            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        setUpPresenter()

        setUpActionListener()

        bindData()

        mPresenter.onUiReady(intent.getStringExtra(NoteActivity.PARM_DOCUMENTID).toString(), this)
    }

    private fun setUpPresenter(){
        mPresenter =   getPresenter<NotePresenterImpl, NoteView>()
    }

    private fun setUpActionListener(){
        btnClose.setOnClickListener {
            mPresenter.onTapSaveNote(intent.getStringExtra(NoteActivity.PARM_DOCUMENTID).toString())

        }

        toolbar.setOnClickListener {
            onBackPressed()
        }

        etConsultationNote.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mPresenter.onTypeNote(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }



    override fun navigateToChat(id: String) {
        startActivity(ChatActivity?.newIntentWithId(this, id))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun displayPateintData(patient: Patient) {
        tvPatientName.text = patient.name

        tvPatientBD.text = patient.bDate


        tvDate.text = currentDate().toString()
    }

    override fun showErrorMessage(error: String) {

    }

    private fun bindData(){

    }
}