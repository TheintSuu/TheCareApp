package com.theintsuhtwe.thecareapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.iid.FirebaseInstanceId
import com.theintsuhtwe.shared.activities.BaseActivity
import com.theintsuhtwe.shared.data.vos.*
import com.theintsuhtwe.thecareapp.R
import com.theintsuhtwe.thecareapp.fragments.ConsultationHistoryFragment
import com.theintsuhtwe.thecareapp.fragments.HomeFragment
import com.theintsuhtwe.thecareapp.fragments.ProfileFragment
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenter
import com.theintsuhtwe.thecareapp.mvp.presenters.HomePresenterImpl
import com.theintsuhtwe.thecareapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  BaseActivity() {


    private var homeFragment = HomeFragment.newInstance("a", "b")

    private var consultationFragment = ConsultationHistoryFragment.newInstance("a", "b")

    private var profileFragment = ProfileFragment.newInstance("a", "b")

    private var activeFragment : Fragment = HomeFragment.newInstance("a", "b")


    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragmentManager()

        callFragment(homeFragment)

        setUpBottomNavigation()


    }

    private fun setUpBottomNavigation(){
        BottomNav.setOnNavigationItemSelectedListener (object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.nav_home -> {
                        callFragment(homeFragment)
                        return true
                    }

                    R.id.nav_history -> {
                        callFragment(consultationFragment)
                        return true
                    }
                    R.id.nav_profile -> {
                        callFragment(profileFragment)
                        return true
                    }

                }
                return false

            }
        })
    }

    private fun setupFragmentManager() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, homeFragment).hide(homeFragment)
            add(R.id.container, consultationFragment).hide(consultationFragment)
            add(R.id.container, profileFragment).hide(profileFragment)
        }.commit()

    }

    fun callFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment

    }
}