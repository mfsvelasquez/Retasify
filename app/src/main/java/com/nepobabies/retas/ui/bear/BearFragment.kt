package com.nepobabies.retas.ui.bear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.donate.DonateFragment

class BearFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI(view)
    }

    private fun setupUI(view: View) {
        val editNameIcon = view.findViewById<ImageView>(R.id.edit_name_icon)
        val customizeButton = view.findViewById<MaterialButton>(R.id.customize_button)
        val donateButton = view.findViewById<MaterialButton>(R.id.donate_button)
        val bearNameText = view.findViewById<TextView>(R.id.bear_name_text)

        // Edit name icon - navigate to clothes page (which has name editing)
        editNameIcon?.setOnClickListener {
            navigateToFragment(ClothesPageFragment())
        }

        // Customize button - navigate to customize bear fragment
        customizeButton?.setOnClickListener {
            navigateToFragment(CustomizeBearFragment())
        }

        // Donate button - navigate to donate fragment
        donateButton?.setOnClickListener {
            navigateToFragment(DonateFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
