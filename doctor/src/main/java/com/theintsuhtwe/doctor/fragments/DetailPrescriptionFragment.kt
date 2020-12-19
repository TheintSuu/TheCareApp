package com.theintsuhtwe.doctor.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.mvp.presenters.impls.PrescriptionPresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.PrescriptionPresenterImpl
import com.theintsuhtwe.doctor.utils.totalMedicines
import kotlinx.android.synthetic.main.fragment_detail_prescription.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailPrescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailPrescriptionFragment: DialogFragment() {

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_Price_ID = "BUNDLE_PATIENT_ID"
        const val BUNDLE_CATEGORY_ID = " BUNDLE_CATEGORY_ID"


        fun newFragment(): DetailPrescriptionFragment {
            return DetailPrescriptionFragment()
        }
    }

    private var mDayMonthWeek = 1

    private var mMorningNight = 1

    private var mBeforeAfter = 1

    private var mQuantityMedicine = 1

    private lateinit var mPresenter: PrescriptionPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_prescription, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        setUpListener()

        setData()

//        view.btnAddPrescription.setOnClickListener {
//            arguments?.getString(DetailPrescriptionFragment.BUNDLE_CATEGORY_ID)?.let { it1 -> arguments?.getLong(DetailPrescriptionFragment.BUNDLE_Price_ID)?.let { it2 ->
//                mPresenter.onTapAdd(it1,
//                    it2)
//            } }
//            dismiss()
//        }

    }

    private fun setUpPresenter() {
//        tvPrescriptionName.text =  arguments?.getString(DetailPrescriptionFragment.BUNDLE_CATEGORY_ID)
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(PrescriptionPresenterImpl::class.java)
        }

    }



   private fun setUpListener(){
       spRoutine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
           override fun onNothingSelected(p0: AdapterView<*>?) {

           }

           override fun onItemSelected(
               parent: AdapterView<*>,
               view: View,
               position: Int,
               id: Long
           ) {
              when(parent.getItemAtPosition(position).toString()){
                  getString(R.string.week) -> {
                      mDayMonthWeek = 7
                  }
                  getString(R.string.month) -> {
                      mDayMonthWeek = 30
                  }
                  else -> {
                      mDayMonthWeek = 1
                  }
              }
           }
       }


   }

    private fun setData(){


        for (index in 0 until cgRoutine.childCount) {
            val chip: Chip = cgRoutine.getChildAt(index) as Chip

            // Set the chip checked change listener
            chip.setOnCheckedChangeListener{view, isChecked ->
                if (isChecked){
                    if(view.text.toString() == "မနက်")  mPresenter.onTapSelectedRoutine("Morning")
                    if(view.text.toString() == "နေ့")  mPresenter.onTapSelectedRoutine("Launch")
                    if(view.text.toString() == "ည")  mPresenter.onTapSelectedRoutine("Night")

                    mMorningNight += 1
                }else{
                    if(view.text.toString() == "မနက်")  mPresenter.onTapRemoveRoutine("Morning")
                    if(view.text.toString() == "နေ့") mPresenter.onTapRemoveRoutine("Launch")
                    if(view.text.toString() == "ည") mPresenter.onTapRemoveRoutine("Night")
                    mMorningNight -= 1
                }


            }
        }

        for (index in 0 until cgBeforeAfter.childCount) {
            val chip: Chip = cgBeforeAfter.getChildAt(index) as Chip

            // Set the chip checked change listener
            chip.setOnCheckedChangeListener{view, isChecked ->
                if (isChecked){
                    mPresenter.onTapMedicineBeforeAfter(view.text.toString())
                   mBeforeAfter += 1
                }else{
                    mPresenter.onTapRemoveMedicineBeforeAfter(view.text.toString())
                    mBeforeAfter -= 1
                }
            }
        }

        btnAddPrescription.setOnClickListener {
            val totalMedicine : Int = totalMedicines(ed_amount.text.toString(), mDayMonthWeek, mBeforeAfter)
            mPresenter.onTapQuantity(totalMedicine.toString())
            arguments?.getLong(BUNDLE_Price_ID)?.toLong()?.let { it1 ->
                mPresenter.onTapAdd(arguments?.getString(BUNDLE_CATEGORY_ID).toString(),
                    it1
                )
            }

            dismiss()
        }

        etNote.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mPresenter.onTapNote(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}
