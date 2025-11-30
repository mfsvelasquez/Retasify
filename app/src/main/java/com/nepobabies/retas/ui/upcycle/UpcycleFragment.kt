package com.nepobabies.retas.ui.upcycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.donate.PartnerDetailFragment

class UpcycleFragment : Fragment() {

    companion object {
        // Partner IDs - matching PartnerDetailFragment constants
        const val PARTNER_KULTURA = "kultura"
        const val PARTNER_COMMUNITY = "community"
        const val PARTNER_GOOD_NEIGHBORS = "good_neighbors"
        const val PARTNER_CANDLE = "candle"
        const val PARTNER_MUNI = "muni"
        const val PARTNER_MAISON = "maison"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupPartnerClicks(view)
    }

    private fun setupPartnerClicks(view: View) {
        // Local Communities
        view.findViewById<ImageView>(R.id.logo_community_1)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_COMMUNITY)
        }
        view.findViewById<ImageView>(R.id.logo_community_2)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_COMMUNITY)
        }
        view.findViewById<ImageView>(R.id.logo_good_neighbors_1)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_GOOD_NEIGHBORS)
        }
        view.findViewById<ImageView>(R.id.logo_good_neighbors_2)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_GOOD_NEIGHBORS)
        }
        view.findViewById<ImageView>(R.id.logo_kultura_1)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_KULTURA)
        }
        view.findViewById<ImageView>(R.id.logo_kultura_2)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_KULTURA)
        }

        // Companies
        view.findViewById<ImageView>(R.id.logo_candle)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_CANDLE)
        }
        view.findViewById<ImageView>(R.id.logo_muni)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_MUNI)
        }
        view.findViewById<ImageView>(R.id.logo_maison)?.setOnClickListener {
            navigateToPartnerDetail(PARTNER_MAISON)
        }
    }

    private fun navigateToPartnerDetail(partnerId: String) {
        val fragment = PartnerDetailFragment.newInstance(partnerId)
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
