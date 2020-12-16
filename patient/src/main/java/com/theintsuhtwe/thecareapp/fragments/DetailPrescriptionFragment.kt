package com.theintsuhtwe.thecareapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.mvp.presenters.PrescriptionPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.PrescriptionPresenterImpl
import kotlinx.android.synthetic.main.fragment_detail_prescription.*
import kotlinx.android.synthetic.main.fragment_detail_prescription.view.*


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


        setData()

        view.btnAddPrescription.setOnClickListener {
            arguments?.getString(DetailPrescriptionFragment.BUNDLE_CATEGORY_ID)?.let { it1 -> arguments?.getLong(DetailPrescriptionFragment.BUNDLE_Price_ID)?.let { it2 ->
                mPresenter.onTapAdd(it1,
                    it2)
            } }
            dismiss()
        }

    }

    private fun setUpPresenter() {
        tvPrescriptionName.text =  arguments?.getString(DetailPrescriptionFragment.BUNDLE_CATEGORY_ID)
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(PrescriptionPresenterImpl::class.java)
        }

    }

    private fun setData(){
        for (index in 0 until cgCategory.childCount) {
            val chip: Chip = cgCategory.getChildAt(index) as Chip

            // Set the chip checked change listener
            chip.setOnCheckedChangeListener{view, isChecked ->
                if (isChecked){
                    mPresenter.onTapSelectedRoutine(chip.text.toString())
                }else{
                    mPresenter.onTapRemoveRoutine(chip.text.toString())
                }


            }
        }



//        for (index in 0 until cgRoutine.childCount) {
//            val chip: Chip = cgRoutine.getChildAt(index) as Chip
//
//            // Set the chip checked change listener
//            chip.setOnCheckedChangeListener{view, isChecked ->
//                if (isChecked){
//                    mPresenter.onTapMedicineBeforeAfter(chip.text.toString())
//                }
//            }
//        }

        view?.etNote?.text.toString().isNotEmpty().let{
            mPresenter.onTapNote(etNote.text.toString())
        }

        view?.etQuantity?.text.isNullOrEmpty().let{
            mPresenter.onTapQuantity(etQuantity.text.toString())
        }
    }
}
