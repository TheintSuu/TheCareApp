package com.theintsuhtwe.thecareapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.theintsuhtwe.shared.data.vos.ConsultationVO
import com.theintsuhtwe.shared.data.vos.MedicineVO
import com.theintsuhtwe.shared.data.vos.QuestionVO
import com.theintsuhtwe.shared.fragments.BaseFragment
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.activities.ChatActivity
import com.theintsuhtwe.thecareapp.adapters.DetailPrescriptionAdapter
import com.theintsuhtwe.thecareapp.adapters.HistoryAdatper
import com.theintsuhtwe.thecareapp.mvp.presenters.HistoryPresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HistoryPresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.ConsultationHistoryView
import com.theintsuhtwe.thecareapp.utils.EMPTY_IMAGE_URL
import com.theintsuhtwe.thecareapp.utils.EM_NO_NEWS_AVAILABLE
import com.theintsuhtwe.thecareapp.utils.SessionManager
import com.theintsuhtwe.thecareapp.views.viewpod.EmptyViewPod
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_special_question.*
import kotlinx.android.synthetic.main.layout_consultion_received.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpecialQuestionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : BaseFragment(), ConsultationHistoryView {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter: HistoryPresenter

    private lateinit var mAdapter : HistoryAdatper

    private lateinit var mViewPodEmpty: EmptyViewPod





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpPresenter()

        setUpListener()

        hideEmptyView()

        setUpRecyclerView()


        setUpViewPods()


            mPresenter.onUiReady(
                SessionManager.patient_id.toString(),this)



    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<HistoryPresenterImpl, ConsultationHistoryView>()

    }

    private fun setUpRecyclerView(){
        mAdapter = HistoryAdatper(mPresenter)


        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvHistory.layoutManager = linearLayoutManager
        rvHistory.adapter = mAdapter
    }

    private fun setUpListener(){


    }

    companion object {

        fun newInstance(param1: String, param2: String) =
                HistoryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun displayCaseSummary(list: List<QuestionVO>) {

    }

    override fun displayGeneral(list: List<QuestionVO>) {

    }

    override fun displayHistory(list: List<ConsultationVO>) {
        hideEmptyView()
        mAdapter.setData(list)
    }

    override fun navigateToChat(id: String) {
        startActivity(activity?.let { ChatActivity.newIntentFromHistory(it,id,  getString(R.string.history) ) })
    }

    override fun displayEmptyView() {
        showEmptyView()
    }

    override fun showPrescriptionDialog(id: String, list : List<MedicineVO>) {
        childFragmentManager?.let {
            val mDialog  =   ConsultationPrescriptionFragment.newFragmentWithList(list)
            val bundle = Bundle()
            bundle.putString(ConsultationPrescriptionFragment.BUNDLE_CATEGORY_ID, id)

            mDialog?.arguments = bundle
            mDialog.show(
                it, ConsultationPrescriptionFragment.BUNDLE_CATEGORY_ID
            )

        }
    }




    override fun navigetToDetailPrescription(id: String) {
       // startActivity(activity?.let { ChatActivity.newIntentFromHistory(it,id,  getString(R.string.history) ) })
    }
    private fun setUpViewPods() {
        mViewPodEmpty = vpEmpty as EmptyViewPod
        mViewPodEmpty.setEmptyData(EM_NO_NEWS_AVAILABLE, EMPTY_IMAGE_URL)
        mViewPodEmpty.setDeledate(mPresenter)
    }

    private fun showEmptyView() {
        vpEmpty.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        vpEmpty.visibility = View.GONE
    }
}