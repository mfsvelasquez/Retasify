package com.nepobabies.retas.ui.donate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.nepobabies.retas.R

/**
 * Fragment to display partner/donation recipient details.
 */
class PartnerDetailFragment : Fragment() {

    private var partnerId: String = ""
    private var onDonateClick: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        partnerId = arguments?.getString(ARG_PARTNER_ID) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_partner_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViews(view)
        loadPartnerData(view)
    }

    private fun setupViews(view: View) {
        val btnDonate = view.findViewById<MaterialButton>(R.id.btn_donate)
        btnDonate.setOnClickListener {
            onDonateClick?.invoke()
            // Navigate back or show confirmation
            parentFragmentManager.popBackStack()
        }

        val btnBack = view.findViewById<ImageButton>(R.id.button_back)
        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun loadPartnerData(view: View) {
        val partnerLogo = view.findViewById<ImageView>(R.id.partner_logo)
        val partnerName = view.findViewById<TextView>(R.id.partner_name)
        val partnerTagline = view.findViewById<TextView>(R.id.partner_tagline)
        val partnerDetails = view.findViewById<TextView>(R.id.partner_details)
        val partnerPhone = view.findViewById<TextView>(R.id.partner_phone)
        val partnerEmail = view.findViewById<TextView>(R.id.partner_email)
        val partnerFacebook = view.findViewById<TextView>(R.id.partner_facebook)

        // Load data based on partner ID
        when (partnerId) {
            PARTNER_KULTURA -> {
                partnerLogo.setImageResource(R.drawable.logo_kultura)
                partnerName.text = "Kultura Filipino"
                partnerTagline.text = "Uniquely Filipino"
                partnerDetails.text = "Kultura Filipino is dedicated to preserving and promoting Filipino heritage through sustainable fashion. We accept pre-loved traditional Filipino garments and fabrics to be repurposed into modern, wearable pieces.\n\nYour donations help support local artisans and keep our cultural heritage alive while promoting sustainable fashion practices."
                partnerPhone.text = "+63 917 123 4567"
                partnerEmail.text = "donate@kulturafilipino.ph"
                partnerFacebook.text = "Kultura Filipino"
            }
            PARTNER_COMMUNITY -> {
                partnerLogo.setImageResource(R.drawable.logo_community)
                partnerName.text = "Scraps for Rags Initiative"
                partnerTagline.text = "Community Connections"
                partnerDetails.text = "We are launching the \"Scraps for Rags\" initiative to collect fabric scraps and transform them into rags and essential cloth items for children and families in need. Many in our community, especially those affected by recent hardships, lack basic necessities.\n\nBy repurposing discarded fabrics, we can provide them with much-needed support while reducing textile waste. This project not only helps those in need but also teaches valuable lessons on sustainability and environmental responsibility to the next generation."
                partnerPhone.text = "+63 918 234 5678"
                partnerEmail.text = "community@scrapsforags.org"
                partnerFacebook.text = "Community Connections PH"
            }
            PARTNER_GOOD_NEIGHBORS -> {
                partnerLogo.setImageResource(R.drawable.logo_good_neighbors)
                partnerName.text = "Good Neighbors International"
                partnerTagline.text = "International Philippines"
                partnerDetails.text = "Good Neighbors International Philippines works to improve the lives of children and communities through education, health, and livelihood programs. We accept clothing donations to support families in underserved communities across the Philippines.\n\nYour pre-loved clothes will be distributed to those who need them most, helping provide warmth and dignity to vulnerable populations."
                partnerPhone.text = "+63 919 345 6789"
                partnerEmail.text = "ph@goodneighbors.org"
                partnerFacebook.text = "Good Neighbors Philippines"
            }
            else -> {
                // Default/fallback data
                partnerName.text = "Donation Partner"
                partnerTagline.text = "Making a difference"
                partnerDetails.text = "This organization accepts clothing donations to help those in need in the community."
                partnerPhone.text = "+63 XXX XXX XXXX"
                partnerEmail.text = "contact@partner.org"
                partnerFacebook.text = "Partner Organization"
            }
        }
    }

    fun setOnDonateClickListener(listener: () -> Unit) {
        onDonateClick = listener
    }

    companion object {
        private const val ARG_PARTNER_ID = "partner_id"
        
        const val PARTNER_KULTURA = "kultura"
        const val PARTNER_COMMUNITY = "community"
        const val PARTNER_GOOD_NEIGHBORS = "good_neighbors"

        fun newInstance(partnerId: String): PartnerDetailFragment {
            return PartnerDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARTNER_ID, partnerId)
                }
            }
        }
    }
}
