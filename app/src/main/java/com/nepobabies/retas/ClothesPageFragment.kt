package com.nepobabies.retas


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ClothesPageFragment : Fragment(R.layout.fragment_clothes_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<ImageButton>(R.id.button_back)

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
