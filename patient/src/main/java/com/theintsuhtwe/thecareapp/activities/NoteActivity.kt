package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.Patient
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.NotePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.NotePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.presenters.PrescriptionPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.NoteView
import com.theintsuhtwe.thecareapp.mvp.views.PrescriptionView
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

        mPresenter.onUiReady(intent.getStringExtra(CaseSummaryActivity.PARM_DOCUMENTID).toString(), this)
    }

    private fun setUpPresenter(){
        mPresenter =   getPresenter<NotePresenterImpl, NoteView>()
    }

    private fun setUpActionListener(){
        btnClose.setOnClickListener {
            mPresenter.onTapSaveNote(intent.getStringExtra(CaseSummaryActivity.PARM_DOCUMENTID).toString())
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

    }

    override fun displayPateintData(patient: Patient) {
        tvPatientName.text = patient.name

        tvPatientBD.text = patient.email
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        tvDate.text = currentDate
    }

    override fun showErrorMessage(error: String) {

    }
}