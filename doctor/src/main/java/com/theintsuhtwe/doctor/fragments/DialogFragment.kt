package com.theintsuhtwe.doctor.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.activities.TimePickerHelper
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenterImpl
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogFragment : DialogFragment() {

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_PATIENT_ID = "BUNDLE_PATIENT_ID"
        const val BUNDLE_CATEGORY_ID = " BUNDLE_CATEGORY_ID"


        fun newFragment(): DialogFragment {
            return DialogFragment()
        }
    }

   // lateinit var timePicker: TimePickerHelper

    private lateinit var mPresenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

     //   timePicker = TimePickerHelper(activity, false, true)



        view.btnConfirm.setOnClickListener {
            // mPresenter.onTapConfirm()
            dismiss()
        }


    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(HomePresenterImpl::class.java)


        }
    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
//        timePicker.showDialog(h, m, object : TimePickerHelper.Callback {
//            override fun onTimeSelected(hourOfDay: Int, minute: Int) {
//                val hourStr = if (hourOfDay < 10) "0${hourOfDay}" else "${hourOfDay}"
//                val minuteStr = if (minute < 10) "0${minute}" else "${minute}"
//
//            }
//        })
    }
}
