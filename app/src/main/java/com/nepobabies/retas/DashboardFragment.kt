package com.nepobabies.retas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.TextView


class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnKnowFootprint = view.findViewById<TextView>(R.id.btnKnowFootprint)
        btnKnowFootprint.setOnClickListener {
            val intent = Intent(requireContext(), FashionCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}
