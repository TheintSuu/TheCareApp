package com.theintsuhtwe.thecareapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.theintsuhtwe.shared.fragments.BaseDialogFragment
import com.theintsuhtwe.shared.mvp.views.BaseView
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.activities.SpecialQuestionActivity
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.dialog_speciality.view.*

class ConfirmDialogFragment :  DialogFragment(){

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_PATIENT_ID = "BUNDLE_PATIENT_ID"
        const val BUNDLE_CATEGORY_ID = " BUNDLE_CATEGORY_ID"


        fun newFragment(): ConfirmDialogFragment {

            return ConfirmDialogFragment()
        }
    }

    private lateinit var mPresenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_speciality, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        view.btnConfirm.setOnClickListener {
            arguments?.getString(BUNDLE_CATEGORY_ID)?.let { it1 -> mPresenter.onTapConfirm(SessionManager.patient_id.toString(), it1) }

        }

        view.btnNo.setOnClickListener {

            dismiss()
        }
    }

    private fun setUpPresenter() {
        activity?.let {
          // mPresenter = getPresenter<HomePresenterImpl, HomeView>()
           mPresenter = ViewModelProviders.of(it).get(HomePresenterImpl::class.java)
        }
    }
}
