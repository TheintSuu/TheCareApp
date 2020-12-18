package com.theintsuhtwe.doctor.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.adapters.DetailPrescriptionAdapter
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenterImpl
import com.theintsuhtwe.shared.data.vos.MedicineVO
import kotlinx.android.synthetic.main.fragment_consultation_prescription.*
import kotlinx.android.synthetic.main.fragment_consultation_prescription.view.*



class ConsultationPrescriptionFragment : DialogFragment() {



    private lateinit var mPresenter: HomePresenter



    private lateinit var mPrescriptionAdapter : DetailPrescriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_consultation_prescription, container, false)
    }

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




        view.btnClose.setOnClickListener {
           dismiss()

        }


    }

    private fun setUpPresenter() {
        parentFragment?.let {
            // mPresenter = getPresenter<HomePresenterImpl, HomeView>()
            mPresenter = ViewModelProviders.of(it).get(HomePresenterImpl::class.java)
        }
    }

    private fun setUpRecyclerView(){
        mPrescriptionAdapter = DetailPrescriptionAdapter()

        val linearLayoutManager2 = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rvPrescriptionList.layoutManager = linearLayoutManager2
        rvPrescriptionList.adapter = mPrescriptionAdapter

        mPrescriptionAdapter.setData(mMedicineVO)
    }


}