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
            PARTNER_CANDLE -> {
                partnerLogo.setImageResource(R.drawable.logo_candle)
                partnerName.text = "Candle Upcycle Studio"
                partnerTagline.text = "Light Up Sustainability"
                partnerDetails.text = "Candle Upcycle Studio transforms old fabrics into beautiful, eco-friendly products. We specialize in creating unique home décor items and accessories from donated textiles.\n\nYour fabric donations help us reduce waste while creating employment opportunities for local artisans."
                partnerPhone.text = "+63 920 456 7890"
                partnerEmail.text = "hello@candleupcycle.ph"
                partnerFacebook.text = "Candle Upcycle Studio"
            }
            PARTNER_MUNI -> {
                partnerLogo.setImageResource(R.drawable.logo_muni)
                partnerName.text = "Muni Sustainable Fashion"
                partnerTagline.text = "Fashion Forward, Earth First"
                partnerDetails.text = "Muni is a sustainable fashion brand that upcycles pre-loved clothing into trendy, one-of-a-kind pieces. We believe in giving every garment a second life.\n\nDonate your old clothes and we'll transform them into stylish new items while minimizing environmental impact."
                partnerPhone.text = "+63 921 567 8901"
                partnerEmail.text = "info@munifashion.ph"
                partnerFacebook.text = "Muni Sustainable Fashion"
            }
            PARTNER_MAISON -> {
                partnerLogo.setImageResource(R.drawable.logo_maison)
                partnerName.text = "Maison Textile Collective"
                partnerTagline.text = "Weaving Communities Together"
                partnerDetails.text = "Maison Textile Collective is a social enterprise that collects fabric donations and partners with local weavers and seamstresses to create sustainable fashion products.\n\nYour donations directly support livelihood programs for marginalized communities while promoting sustainable fashion practices."
                partnerPhone.text = "+63 922 678 9012"
                partnerEmail.text = "contact@maisontextile.ph"
                partnerFacebook.text = "Maison Textile Collective"
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
        const val PARTNER_CANDLE = "candle"
        const val PARTNER_MUNI = "muni"
        const val PARTNER_MAISON = "maison"

        fun newInstance(partnerId: String): PartnerDetailFragment {
            return PartnerDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARTNER_ID, partnerId)
                }
            }
        }
    }
}
