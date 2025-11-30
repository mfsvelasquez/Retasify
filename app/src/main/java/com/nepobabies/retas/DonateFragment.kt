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
            startActivity(gotodonatedetails)
        }

        emojiGood.setOnClickListener {
            startActivity(gotodonatedetails)
        }

        emojiOkay.setOnClickListener {
            startActivity(gotodonatedetails)
        }

        emojiBad.setOnClickListener {
            startActivity(gotodonatedetails)
        }

        return view
    }
}
