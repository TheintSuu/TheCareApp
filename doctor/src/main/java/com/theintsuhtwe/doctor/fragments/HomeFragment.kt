package com.theintsuhtwe.doctor.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.doctor.R
import com.theintsuhtwe.doctor.activities.ChatActivity
import com.theintsuhtwe.doctor.activities.LoginActivity
import com.theintsuhtwe.doctor.adapters.ConsultationHistoryAdapter
import com.theintsuhtwe.doctor.adapters.ConsultationRequestAdapter
import com.theintsuhtwe.doctor.adapters.DetailPrescriptionAdapter
import com.theintsuhtwe.doctor.fragments.DialogFragment.Companion.BUNDLE_DOCTOR_ID
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenter
import com.theintsuhtwe.doctor.mvp.presenters.impls.HomePresenterImpl
import com.theintsuhtwe.doctor.mvp.views.HomeView
import com.theintsuhtwe.doctor.utils.EMPTY_IMAGE_URL
import com.theintsuhtwe.doctor.utils.EM_NO_NEWS_AVAILABLE
import com.theintsuhtwe.doctor.utils.SessionManager
import com.theintsuhtwe.doctor.views.viewpods.EmptyViewPod
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.fragment_consultation_prescription.*
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), HomeView {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter: HomePresenter

    private lateinit var mAdapter : ConsultationRequestAdapter

    private lateinit var mHistoryAdapter : ConsultationHistoryAdapter

    private lateinit var mViewPodEmpty: EmptyViewPod






    private var mConfirmDialogFragment: DialogFragment? = null

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

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpRecyclerView()

        hideEmptyView()

        setUpListener()

        setUpViewPods()

        bindData()


        activity?.let { mPresenter.onUiReady(it, this) }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setUpRecyclerView(){
        mAdapter = ConsultationRequestAdapter(mPresenter)
        mHistoryAdapter = ConsultationHistoryAdapter(mPresenter)


        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val gridLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvRequest.layoutManager = linearLayoutManager
        rvRequest.adapter = mAdapter

        rvHistory.layoutManager = gridLayoutManager
        rvHistory.adapter = mHistoryAdapter


    }

    private fun setUpListener(){
        btnSignOut.setOnClickListener {
            mPresenter.onTapSignOut()
        }
    }
    
    private  fun setUpPresenter(){
        mPresenter = getPresenter<HomePresenterImpl, HomeView>()
    }

    private fun getToken(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Token", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg ="token :  $token"
                Log.i("Token ", msg)
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()

            })
    }



    override fun displayConsultationHistory(history: List<ConsultationVO>) {
       mHistoryAdapter.setData(history)
    }

    override fun displayRecentConsultation(history: List<ConsultationRequest>) {
       mAdapter.appendData(history)
    }

    override fun displayConsultationConfirm(history: ConsultationRequest) {

    }

    override fun displayConsultationRequest(request: List<ConsultationRequest>) {
        mAdapter.setData(request)
        when(request.isEmpty()){
            true -> hideConsultationRequest()
            else -> showConsultationRequestList(request)
        }
    }

    override fun displayEmptyView() {
        showEmptyView()
    }

    override fun removeConsultationRequest(request: ConsultationRequest) {
        mAdapter.removeData(request)
    }

    override fun displayPrescriptionDialog(id: String, list : List<MedicineVO>) {
        childFragmentManager?.let {
            val mDialog  =   ConsultationPrescriptionFragment.newFragmentWithList(list)
            val bundle = Bundle()

            mPresenter.onDialogUiReady(id)

            bundle.putString(BUNDLE_DOCTOR_ID, id)


            mDialog?.arguments = bundle
            mDialog?.show(
                it,  BUNDLE_DOCTOR_ID
            )

        }
    }



    override fun showDialog() {
        childFragmentManager?.let {
            val mDialog  =   DialogFragment.newFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_DOCTOR_ID, SessionManager.doctor_id)

            mDialog?.arguments = bundle
            mDialog?.show(
                    it,  BUNDLE_DOCTOR_ID
            )

        }
    }

    override fun showDialogNote(note : String) {
        childFragmentManager?.let {
            val mDialog  =   NoteFragment.newFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_DOCTOR_ID, note)

            mDialog?.arguments = bundle
            mDialog?.show(
                it,  BUNDLE_DOCTOR_ID
            )

        }
    }

    override fun navigateToLogin() {
        startActivity(activity?.let { LoginActivity.newIntent(it) })
    }

    override fun navigateToChatActivity(id: String) {
        startActivity(activity?.let { ChatActivity.newIntentWithId(it, id) })
    }

    override fun navigateToNoteActivity(id: String) {

    }

    override fun navigateToChatHistoryActivity(id: String, type: String) {
        hideEmptyView()
        startActivity(activity?.let { ChatActivity.newIntentWithType(it, id, type) })
    }

    private  fun showConsultationRequestList(request : List<ConsultationRequest>){
        rvRequest.visibility = View.VISIBLE
    }

    private fun hideConsultationRequest(){
        rvRequest.visibility = View.GONE
    }


    private fun bindData(){
        Glide.with(this)
                .load(SessionManager.doctor_image.toString())
                .optionalFitCenter()
                .into(ivConfirmDoctorImage)

        tvRecentDoctor.text = SessionManager.doctor_name.toString()


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