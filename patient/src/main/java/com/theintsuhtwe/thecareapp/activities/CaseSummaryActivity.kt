package com.theintsuhtwe.thecareapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.thecareapp.R

class CaseSummaryActivity : BaseActivity() {
    companion object{
        const val PARM_DOCUMENTID = "Document ID"
        fun newIntent(context: Context): Intent {
            return Intent(context, CaseSummaryActivity::class.java)
        }

        fun newIntent(context: Context, id : String): Intent {
            val intent =  Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(PARM_DOCUMENTID, id)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_summary)
    }
}