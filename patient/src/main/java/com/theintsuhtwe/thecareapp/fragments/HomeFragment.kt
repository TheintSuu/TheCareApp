package com.theintsuhtwe.thecareapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
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
import com.theintsuhtwe.thecareapp.adapters.RecentDoctorItemDoctorAdapter
import com.theintsuhtwe.thecareapp.adapters.SpecialityItemAdapter
import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment.Companion.BUNDLE_CATEGORY_ID
import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment.Companion.BUNDLE_PATIENT_ID
import com.theintsuhtwe.thecareapp.fragments.ConfirmDialogFragment.Companion.TAG_ADD_GROCERY_DIALOG
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
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

    private var mConfirmDialogFragment: ConfirmDialogFragment? = null

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

    override fun displayQuestions(qustions: List<QuestionVO>) {

    }

    override fun navigateToQuestion(id: String, category: String) {

    }

    override fun showConfirmDialog(id: String) {
        activity?.supportFragmentManager?.let {
            val mDialog  =   ConfirmDialogFragment.newFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_PATIENT_ID, "patient000")
            bundle.putString(BUNDLE_CATEGORY_ID, id)

            mDialog?.arguments = bundle
            mDialog?.show(
                it,  TAG_ADD_GROCERY_DIALOG
            )

        }
    }

    override fun showConsultationRecevied(doctor: DoctorVO) {
      showConsultationReceived(doctor)
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
        mPresenter.onTapCategory("category001")

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
        rvRecentDoctor.visibility = View.GONE
    }

    private fun showConsultationReceived(doctor: DoctorVO){
        tvDoctorName.text = doctor.name
        tvDoctorSpecialityBiography.text = doctor.biography
        activity?.let {
            Glide.with(it)
                .load(doctor.image)
                .optionalFitCenter()
                .into(ivConfirmDoctorImage)
        }
        tvConfirmMessage.text = getString(R.string.message_receive) + doctor.name
        tvDoctorSpeciality.text = doctor.specialities
        layout_confirm_receive.visibility = View.VISIBLE
    }

    private fun hideConsultationReceived(){

        layout_confirm_receive.visibility = View.GONE
    }
}