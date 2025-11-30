package com.nepobabies.retas.ui.donate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.bear.BearFragment
import com.nepobabies.retas.ui.dashboard.DashboardFragment
import com.nepobabies.retas.ui.profile.ProfileFragment
import com.nepobabies.retas.ui.upcycle.UpcycleFragment

class DonateDetailsFragment : Fragment() {

    companion object {
        private const val ARG_CONDITION = "condition"

        fun newInstance(condition: String): DonateDetailsFragment {
            return DonateDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CONDITION, condition)
                }
            }
        }
    }

    private var condition: String = "great"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        condition = arguments?.getString(ARG_CONDITION) ?: "great"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donate_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBackButton(view)
        setupEmojiIndicators(view)
        setupPartnerClickListeners(view)
        setupBottomNavigation(view)
    }

    private fun setupBackButton(view: View) {
        view.findViewById<ImageButton>(R.id.button_back)?.setOnClickListener {
            // Go back to DonateFragment
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupEmojiIndicators(view: View) {
        val emojiGreat = view.findViewById<ImageView>(R.id.emojiGreatIndicator)
        val emojiGood = view.findViewById<ImageView>(R.id.emojiGoodIndicator)
        val emojiOkay = view.findViewById<ImageView>(R.id.emojiOkayIndicator)
        val emojiBad = view.findViewById<ImageView>(R.id.emojiBadIndicator)
        val conditionMessage = view.findViewById<TextView>(R.id.conditionMessage)

        // Reset all to gray
        emojiGreat?.setImageResource(R.drawable.greatgray)
        emojiGood?.setImageResource(R.drawable.goodgray)
        emojiOkay?.setImageResource(R.drawable.notbadgray)
        emojiBad?.setImageResource(R.drawable.sadgray)

        // Highlight selected emoji and set message
        when (condition) {
            "great" -> {
                emojiGreat?.setImageResource(R.drawable.great)
                conditionMessage?.text = "Great! Your pre-loved apparels deserve a new home!"
            }
            "good" -> {
                emojiGood?.setImageResource(R.drawable.good)
                conditionMessage?.text = "Good condition! Someone will love these clothes!"
            }
            "okay" -> {
                emojiOkay?.setImageResource(R.drawable.notbad)
                conditionMessage?.text = "Not bad! These can still be useful to others."
            }
            "bad" -> {
                emojiBad?.setImageResource(R.drawable.sad)
                conditionMessage?.text = "Needs repair, but can still be upcycled!"
            }
        }
    }

    private fun setupPartnerClickListeners(view: View) {
        Log.d("DonateDetailsFragment", "Setting up partner click listeners")
        
        val partnerKultura = view.findViewById<ShapeableImageView>(R.id.partner_kultura)
        val partnerCommunity = view.findViewById<ShapeableImageView>(R.id.partner_community)
        val partnerGoodNeighbors = view.findViewById<ShapeableImageView>(R.id.partner_good_neighbors)

        Log.d("DonateDetailsFragment", "partnerKultura: $partnerKultura")
        Log.d("DonateDetailsFragment", "partnerCommunity: $partnerCommunity")
        Log.d("DonateDetailsFragment", "partnerGoodNeighbors: $partnerGoodNeighbors")

        partnerKultura?.setOnClickListener {
            Log.d("DonateDetailsFragment", "Kultura clicked!")
            navigateToPartnerDetail(PartnerDetailFragment.PARTNER_KULTURA)
        }

        partnerCommunity?.setOnClickListener {
            Log.d("DonateDetailsFragment", "Community clicked!")
            navigateToPartnerDetail(PartnerDetailFragment.PARTNER_COMMUNITY)
        }

        partnerGoodNeighbors?.setOnClickListener {
            Log.d("DonateDetailsFragment", "Good Neighbors clicked!")
            navigateToPartnerDetail(PartnerDetailFragment.PARTNER_GOOD_NEIGHBORS)
        }
    }

    private fun navigateToPartnerDetail(partnerId: String) {
        Log.d("DonateDetailsFragment", "navigateToPartnerDetail called with partnerId: $partnerId")
        val fragment = PartnerDetailFragment.newInstance(partnerId)
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupBottomNavigation(view: View) {
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        
        // Set donate as selected
        bottomNav?.selectedItemId = R.id.donate

        bottomNav?.setOnItemSelectedListener { menuItem ->
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
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
