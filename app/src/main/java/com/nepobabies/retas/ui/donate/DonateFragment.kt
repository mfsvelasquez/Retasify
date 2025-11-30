package com.nepobabies.retas.ui.donate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nepobabies.retas.R

class DonateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donate, container, false)

        setupConditionSelectors(view)

        return view
    }

    private fun setupConditionSelectors(view: View) {
        val emojiGreat = view.findViewById<ImageView>(R.id.emojiGreat)
        val emojiGood = view.findViewById<ImageView>(R.id.emojiGood)
        val emojiOkay = view.findViewById<ImageView>(R.id.emojiOkay)
        val emojiBad = view.findViewById<ImageView>(R.id.emojiBad)

        emojiGreat.setOnClickListener {
            Toast.makeText(requireContext(), "Condition: Great", Toast.LENGTH_SHORT).show()
            navigateToDonateDetails("great")
        }

        emojiGood.setOnClickListener {
            Toast.makeText(requireContext(), "Condition: Good", Toast.LENGTH_SHORT).show()
            navigateToDonateDetails("good")
        }

        emojiOkay.setOnClickListener {
            Toast.makeText(requireContext(), "Condition: Okay", Toast.LENGTH_SHORT).show()
            navigateToDonateDetails("okay")
        }

        emojiBad.setOnClickListener {
            Toast.makeText(requireContext(), "Condition: Needs Repair", Toast.LENGTH_SHORT).show()
            navigateToDonateDetails("bad")
        }
    }

    private fun navigateToDonateDetails(condition: String) {
        val fragment = DonateDetailsFragment.newInstance(condition)
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
