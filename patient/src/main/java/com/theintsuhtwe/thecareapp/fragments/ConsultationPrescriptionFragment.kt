package com.theintsuhtwe.thecareapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.fragments.BaseFragment
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.adapters.DetailPrescriptionAdapter
import com.theintsuhtwe.thecareapp.mvp.presenters.HistoryPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HistoryPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.dialog_speciality.view.*
import kotlinx.android.synthetic.main.fragment_consultation_prescription.*
import kotlinx.android.synthetic.main.fragment_consultation_prescription.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ConsultationPrescriptionFragment : DialogFragment() {

    private lateinit var mPrescriptionAdapter : DetailPrescriptionAdapter

    private lateinit var mPresenter: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_consultation_prescription, container, false)
    }

//    override fun onStart() {
//        super.onStart()
//        dialog?.window?.setLayout(
//                WindowManager.LayoutParams.MATCH_PARENT+20,
//                WindowManager.LayoutParams.MATCH_PARENT-50
//        )
//        dialog?.apply {
//            setCancelable(false)
//            window?.setBackgroundDrawableResource(android.R.color.transparent)
//        }
//    }

    companion object {
        const val BUNDLE_CATEGORY_ID = " BUNDLE_CATEGORY_ID"
        var mMedicineVO : List<MedicineVO> = arrayListOf()
        fun newFragmentWithList(list : List<MedicineVO>): ConsultationPrescriptionFragment {
            mMedicineVO = list
            return ConsultationPrescriptionFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        setUpRecyclerView()

        arguments?.getString(ConsultationPrescriptionFragment.BUNDLE_CATEGORY_ID)?.let {
            mPresenter.onDialogUiReady(
                it
            )
        }


        view.btnClose.setOnClickListener {
           dismiss()

        }


    }

    private fun setUpPresenter() {
        parentFragment?.let {
            // mPresenter = getPresenter<HomePresenterImpl, HomeView>()
            mPresenter = ViewModelProviders.of(it).get(HistoryPresenterImpl::class.java)
            mPrescriptionAdapter = DetailPrescriptionAdapter()
        }
    }

    private  fun setUpRecyclerView(){
        val linearLayoutManager2 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rvPrescriptionList.layoutManager = linearLayoutManager2
        rvPrescriptionList.adapter = mPrescriptionAdapter

        mPrescriptionAdapter.setData(mMedicineVO)
    }
}