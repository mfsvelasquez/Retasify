package com.nepobabies.retas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class DonateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_donate, container, false)

        val emojiGreat = view.findViewById<ImageView>(R.id.emojiGreat)
        val emojiGood = view.findViewById<ImageView>(R.id.emojiGood)
        val emojiOkay = view.findViewById<ImageView>(R.id.emojiOkay)
        val emojiBad = view.findViewById<ImageView>(R.id.emojiBad)

        val gotodonatedetails = Intent(activity, DonateDetails::class.java)

        emojiGreat.setOnClickListener {
            Toast.makeText(requireContext(), "You selected the great face!", Toast.LENGTH_SHORT).show()
            startActivity(gotodonatedetails)
        }

        emojiGood.setOnClickListener {
            Toast.makeText(requireContext(), "You selected the good face!", Toast.LENGTH_SHORT).show()
            startActivity(gotodonatedetails)
        }

        emojiOkay.setOnClickListener {
            Toast.makeText(requireContext(), "You selected the not bad face!", Toast.LENGTH_SHORT).show()
            startActivity(gotodonatedetails)
        }

        emojiBad.setOnClickListener {
            Toast.makeText(requireContext(), "You selected the bad face!", Toast.LENGTH_SHORT).show()
            startActivity(gotodonatedetails)
        }

        return view
    }
}
