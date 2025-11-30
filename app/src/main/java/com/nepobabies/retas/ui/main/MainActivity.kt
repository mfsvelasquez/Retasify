package com.nepobabies.retas.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.bear.BearFragment
import com.nepobabies.retas.ui.dashboard.DashboardFragment
import com.nepobabies.retas.ui.donate.DonateFragment
import com.nepobabies.retas.ui.profile.ProfileFragment
import com.nepobabies.retas.ui.upcycle.UpcycleFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Show DashboardFragment as default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, DashboardFragment())
                .commit()
            bottomNavigationView.selectedItemId = R.id.dashboard
        }

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.upcycle -> {
                    replaceFragment(UpcycleFragment())
                    true
                }
                R.id.donate -> {
                    replaceFragment(DonateFragment())
                    true
                }
                R.id.dashboard -> {
                    replaceFragment(DashboardFragment())
                    true
                }
                R.id.bear -> {
                    replaceFragment(BearFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
