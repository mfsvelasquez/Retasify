package com.nepobabies.retas.ui.bear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.nepobabies.retas.R
import com.nepobabies.retas.data.repository.BearCustomizationRepository
import com.nepobabies.retas.ui.donate.DonateFragment
import kotlinx.coroutines.launch

class BearFragment : Fragment() {

    private lateinit var repository: BearCustomizationRepository
    
    // Bear overlay views
    private var overlayScarf: ImageView? = null
    private var overlayShirt: ImageView? = null
    private var overlayBottom: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        repository = BearCustomizationRepository.getInstance(requireContext())
        
        initOverlayViews(view)
        setupUI(view)
    }
    
    override fun onResume() {
        super.onResume()
        // Reload saved customization when returning to this fragment
        loadSavedCustomization()
    }
    
    private fun initOverlayViews(view: View) {
        overlayScarf = view.findViewById(R.id.overlay_scarf)
        overlayShirt = view.findViewById(R.id.overlay_shirt)
        overlayBottom = view.findViewById(R.id.overlay_bottom)
    }
    
    private fun loadSavedCustomization() {
        lifecycleScope.launch {
            val customization = repository.getBearCustomization()
            customization?.let { saved ->
                // Restore scarf
                saved.scarfId?.let { scarfId ->
                    val scarf = ClothingItemsProvider.getAllClothingItems().find { it.id == scarfId }
                    scarf?.let {
                        overlayScarf?.setImageResource(it.drawableResId)
                        overlayScarf?.visibility = View.VISIBLE
                        overlayScarf?.scaleX = saved.scarfScale
                        overlayScarf?.scaleY = saved.scarfScale
                        overlayScarf?.translationX = saved.scarfOffsetX
                        overlayScarf?.translationY = saved.scarfOffsetY
                    }
                } ?: run {
                    overlayScarf?.visibility = View.GONE
                }
                
                // Restore shirt
                saved.shirtId?.let { shirtId ->
                    val shirt = ClothingItemsProvider.getAllClothingItems().find { it.id == shirtId }
                    shirt?.let {
                        overlayShirt?.setImageResource(it.drawableResId)
                        overlayShirt?.visibility = View.VISIBLE
                        overlayShirt?.scaleX = saved.shirtScale
                        overlayShirt?.scaleY = saved.shirtScale
                        overlayShirt?.translationX = saved.shirtOffsetX
                        overlayShirt?.translationY = saved.shirtOffsetY
                    }
                } ?: run {
                    overlayShirt?.visibility = View.GONE
                }
                
                // Restore bottom
                saved.bottomId?.let { bottomId ->
                    val bottom = ClothingItemsProvider.getAllClothingItems().find { it.id == bottomId }
                    bottom?.let {
                        overlayBottom?.setImageResource(it.drawableResId)
                        overlayBottom?.visibility = View.VISIBLE
                        overlayBottom?.scaleX = saved.bottomScale
                        overlayBottom?.scaleY = saved.bottomScale
                        overlayBottom?.translationX = saved.bottomOffsetX
                        overlayBottom?.translationY = saved.bottomOffsetY
                    }
                } ?: run {
                    overlayBottom?.visibility = View.GONE
                }
            } ?: run {
                // No saved customization, hide all overlays
                overlayScarf?.visibility = View.GONE
                overlayShirt?.visibility = View.GONE
                overlayBottom?.visibility = View.GONE
            }
        }
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
