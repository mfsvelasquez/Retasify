package com.nepobabies.retas.ui.bear

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.nepobabies.retas.R

class ClothesPageFragment : Fragment(R.layout.fragment_clothes_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI(view)
    }

    private fun setupUI(view: View) {
        // Back button - navigate back to BearFragment
        val backButton = view.findViewById<ImageButton>(R.id.button_back)
        backButton?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Donate button - navigate to DonateFragment
        val donateButton = view.findViewById<AppCompatButton>(R.id.button_donate)
        donateButton?.setOnClickListener {
            navigateToFragment(com.nepobabies.retas.ui.donate.DonateFragment())
        }

        // TODO: Save bear name and message to local storage or database
        // val nameEditText = view.findViewById<EditText>(R.id.edittext_name)
        // val messageEditText = view.findViewById<EditText>(R.id.edittext_message)
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
