package com.nepobabies.retas.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nepobabies.retas.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupProfileUI(view)
    }

    private fun setupProfileUI(view: View) {
        // TODO: Load user profile from Firebase
        // - Profile picture
        // - Username  
        // - Donation stats
        // - Achievements/badges
        // - Settings
        // - Add logout button to layout (R.id.logoutButton)
        
        // Setup profile data with existing views
        val profileTitle = view.findViewById<TextView>(R.id.profile_title)
        // TODO: Set actual user name from Firebase
        profileTitle?.text = "User Name"
    }
}
