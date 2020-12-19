package com.theintsuhtwe.thecareapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.shared.fragments.BaseFragment
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.activities.ChatActivity
import com.theintsuhtwe.thecareapp.activities.QuestionActivity
import com.theintsuhtwe.thecareapp.adapters.RecentDoctorItemDoctorAdapter
import com.theintsuhtwe.thecareapp.adapters.SpecialityItemAdapter
import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment.Companion.BUNDLE_CATEGORY_ID
import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment.Companion.BUNDLE_ID
import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment.Companion.TAG_ADD_GROCERY_DIALOG
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import com.theintsuhtwe.thecareapp.utils.SessionManager
import kotlinx.android.synthetic.main.activity_special_question.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.rvSpecial
import kotlinx.android.synthetic.main.item_speciality.view.*
import kotlinx.android.synthetic.main.layout_consultion_received.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : BaseFragment(), HomeView {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter: HomePresenter

    private lateinit var mAdapter : SpecialityItemAdapter

    private lateinit var mRecentDoctorAdapter : RecentDoctorItemDoctorAdapter

    private var mDialog: ConfirmDialogFragment? = null

    private var mConsultationId = ""

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

        setUpListener()

        hideRecentDoctor()

        hideConsultationReceived()

//        SessionManager.request_id = param1

        mPresenter.onUiReady(this)
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

    @SuppressLint("LongLogTag")
    override fun displaySpecialities(category: List<CategoryVO>) {
       mAdapter.setData(category)
    }

    @SuppressLint("LongLogTag")
    override fun displayDoctorBySpecialities(doctor: List<DoctorVO>) {
        Log.d("Broadcast Request to Suitable Doctor", doctor.toMutableList().toString())
       // mPresenter.onTapConsultationRequest(CaseSummaryVO(), Patient(), CategoryVO())

    }

    override fun displayConsultationConfirm(doctor: DoctorVO) {

    }

    override fun displayQuestions() {
      //  startActivity( activity?.applicationContext?.let{QuestionActivity.newIntent(it)})
    }

    override fun navigateToQuestion(id: String, special : String) {
        startActivity(activity?.applicationContext?.let { QuestionActivity.newIntent(it, special) })
    }

    override fun navigateToChat(id: String) {
        startActivity(activity?.let { ChatActivity.newIntentWithId(it, id) })
    }

    override fun showConfirmDialog(spcial : String) {
        childFragmentManager?.let {
            SessionManager.patient_recent_doctor_id = ""
            val mDialog  =   ConfirmDialogFragment.newFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_CATEGORY_ID, spcial)

            mDialog?.arguments = bundle
            mDialog?.show(
                it,  BUNDLE_CATEGORY_ID
            )

        }
    }

    override fun showRecentConfirmDialog(id: String,special: String) {
        childFragmentManager?.let {
            val mDialog  =   ConfirmDialogFragment.newFragment()
            val bundle = Bundle()
            (SessionManager).patient_recent_doctor_id = id
            bundle.putString(BUNDLE_CATEGORY_ID, special)

            mDialog?.arguments = bundle
            mDialog?.show(
                it,  BUNDLE_CATEGORY_ID
            )

        }
    }

    override fun showConsultationRecevied(consulation: ConsultationRequest) {
        showConsultationReceived(consulation)
    }


    override fun displayRecentDoctorList(doctors: List<DoctorVO>) {

     when(doctors.isEmpty()){
         true -> hideRecentDoctor()
         else -> showRecentDoctor()
     }
        mRecentDoctorAdapter.setData(doctors)
    }

    private fun setUpPresenter(){
        mPresenter = getPresenter<HomePresenterImpl, HomeView>()

    }

    private fun setUpRecyclerView(){
        mAdapter = SpecialityItemAdapter(mPresenter)

        mRecentDoctorAdapter = RecentDoctorItemDoctorAdapter(mPresenter)

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        rvRecentDoctor.layoutManager = linearLayoutManager
        rvRecentDoctor.adapter = mRecentDoctorAdapter

        rvSpecial.layoutManager = gridLayoutManager
        rvSpecial.adapter = mAdapter
    }

    private fun setUpListener(){
    btnStartConsultation.setOnClickListener {
        mPresenter.onTapStartConsulataion(SessionManager.request_id.toString(), mConsultationId)
      //  startActivity(activity?.let { it1 -> ChatActivity.newIntentWithId(it1, mConsultationId) })
    }


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

    private fun showRecentDoctor(){
        rvRecentDoctor.visibility = View.VISIBLE
        tvRecentDoctor.visibility = View.VISIBLE

    }

    private fun hideRecentDoctor(){
        rvRecentDoctor.visibility = View.GONE
        tvRecentDoctor.visibility = View.GONE
    }

    private fun showConsultationReceived(consulation: ConsultationRequest){
        tvDoctorName.text = consulation.doctor?.name
        tvDoctorSpecialityBiography.text =  consulation.doctor?.biography

        activity?.let {
            Glide.with(it)
                .load( consulation.doctor?.image)
                .into(ivConfirmDoctorImage)
        }
        mConsultationId = consulation.consultationId.toString()
        tvConfirmMessage.text = getString(R.string.message_receive) + " " +  consulation.doctor?.name + getString(R.string.message_received)
        tvDoctorSpeciality.text =  consulation.doctor?.specialities
        layout_confirm_receive.visibility = View.VISIBLE
    }

    private fun hideConsultationReceived(){

        layout_confirm_receive.visibility = View.GONE
    }
}