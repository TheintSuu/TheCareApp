package com.theintsuhtwe.doctor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenterImpl
import kotlinx.android.synthetic.main.fragment_dialog_note.*
import kotlinx.android.synthetic.main.fragment_dialog_note.view.*


class NoteFragment : DialogFragment() {

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_DOCTOR_ID = "BUNDLE_DOCTOR_ID"



        fun newFragment():NoteFragment {
            return NoteFragment()
        }
    }



    private lateinit var mPresenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_note, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()





        view.btnClose.setOnClickListener {
            // mPresenter.onTapConfirm()
            dismiss()
        }

        arguments?.getString(BUNDLE_DOCTOR_ID).toString().let {
            tvNote.text = it
        }


    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(HomePresenterImpl::class.java)

        }
    }


}