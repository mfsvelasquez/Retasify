package com.nepobabies.retas.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.calculator.FashionCalculatorActivity

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup user greeting
        val userNameText = view.findViewById<TextView>(R.id.user_name_text)
        // TODO: Get user name from Firebase/SharedPreferences
        userNameText?.text = "User!"

        // Setup Fashion Footprint card click
        val fashionFootprintCard = view.findViewById<MaterialCardView>(R.id.fashion_footprint_card)
        fashionFootprintCard?.setOnClickListener {
            startActivity(Intent(requireContext(), FashionCalculatorActivity::class.java))
        }
    }
}
