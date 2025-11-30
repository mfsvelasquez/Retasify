package com.nepobabies.retas.ui.bear

import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nepobabies.retas.R
import com.nepobabies.retas.data.local.BearCustomizationEntity
import com.nepobabies.retas.data.repository.BearCustomizationRepository
import kotlinx.coroutines.launch

class CustomizeBearFragment : Fragment(R.layout.fragment_customize_bear) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClothingAdapter
    
    private lateinit var tabScarves: TextView
    private lateinit var tabShirts: TextView
    private lateinit var tabBottoms: TextView
    
    private lateinit var bearContainer: FrameLayout
    private lateinit var overlayScarf: ImageView
    private lateinit var overlayShirt: ImageView
    private lateinit var overlayBottom: ImageView
    
    // Adjustment controls
    private lateinit var adjustmentControls: LinearLayout
    private lateinit var adjustmentLabel: TextView
    private lateinit var btnSizeUp: ImageButton
    private lateinit var btnSizeDown: ImageButton
    private lateinit var btnMoveUp: ImageButton
    private lateinit var btnMoveDown: ImageButton
    private lateinit var btnMoveLeft: ImageButton
    private lateinit var btnMoveRight: ImageButton
    
    // Save/Reset buttons
    private lateinit var btnSave: ImageButton
    private lateinit var btnReset: ImageButton
    
    // Repository for database operations
    private lateinit var repository: BearCustomizationRepository
    
    private var currentCategory = ClothingCategory.SCARF
    
    // Store selected clothing items
    private var selectedScarf: ClothingItem? = null
    private var selectedShirt: ClothingItem? = null
    private var selectedBottom: ClothingItem? = null
    
    // Size scale factors for each category (1.0 = default)
    private var scarfScale = 1.0f
    private var shirtScale = 1.0f
    private var bottomScale = 1.0f
    
    // Position offsets for each category
    private var scarfOffsetX = 0f
    private var scarfOffsetY = 0f
    private var shirtOffsetX = 0f
    private var shirtOffsetY = 0f
    private var bottomOffsetX = 0f
    private var bottomOffsetY = 0f
    
    // Currently selected overlay for adjustment
    private var selectedOverlay: ClothingCategory? = null
    
    // Movement step in dp
    private val MOVE_STEP = 5f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize repository
        repository = BearCustomizationRepository.getInstance(requireContext())
        
        initViews(view)
        setupBackButton(view)
        setupSaveResetButtons(view)
        setupTabs()
        setupRecyclerView()
        setupDragAndDrop()
        setupAdjustmentControls()
        setupOverlayClickListeners()
        
        // Load saved customization
        loadSavedCustomization()
        
        // Show scarves by default
        showCategory(ClothingCategory.SCARF)
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_clothing)
        tabScarves = view.findViewById(R.id.tab_scarves)
        tabShirts = view.findViewById(R.id.tab_shirts)
        tabBottoms = view.findViewById(R.id.tab_bottoms)
        bearContainer = view.findViewById(R.id.bear_container)
        overlayScarf = view.findViewById(R.id.overlay_scarf)
        overlayShirt = view.findViewById(R.id.overlay_shirt)
        overlayBottom = view.findViewById(R.id.overlay_bottom)
        
        // Adjustment controls
        adjustmentControls = view.findViewById(R.id.adjustment_controls)
        adjustmentLabel = view.findViewById(R.id.adjustment_label)
        btnSizeUp = view.findViewById(R.id.btn_size_up)
        btnSizeDown = view.findViewById(R.id.btn_size_down)
        btnMoveUp = view.findViewById(R.id.btn_move_up)
        btnMoveDown = view.findViewById(R.id.btn_move_down)
        btnMoveLeft = view.findViewById(R.id.btn_move_left)
        btnMoveRight = view.findViewById(R.id.btn_move_right)
        
        // Save/Reset buttons
        btnSave = view.findViewById(R.id.button_save)
        btnReset = view.findViewById(R.id.button_reset)
    }

    private fun setupBackButton(view: View) {
        view.findViewById<ImageButton>(R.id.button_back)?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
    
    private fun setupSaveResetButtons(view: View) {
        btnSave.setOnClickListener {
            saveBearCustomization()
        }
        
        btnReset.setOnClickListener {
            resetBearCustomization()
        }
    }
    
    private fun loadSavedCustomization() {
        lifecycleScope.launch {
            val customization = repository.getBearCustomization()
            customization?.let { saved ->
                // Restore scarf
                saved.scarfId?.let { scarfId ->
                    val scarf = ClothingItemsProvider.getAllClothingItems().find { it.id == scarfId }
                    scarf?.let {
                        selectedScarf = it
                        overlayScarf.setImageResource(it.drawableResId)
                        overlayScarf.visibility = View.VISIBLE
                    }
                }
                
                // Restore shirt
                saved.shirtId?.let { shirtId ->
                    val shirt = ClothingItemsProvider.getAllClothingItems().find { it.id == shirtId }
                    shirt?.let {
                        selectedShirt = it
                        overlayShirt.setImageResource(it.drawableResId)
                        overlayShirt.visibility = View.VISIBLE
                    }
                }
                
                // Restore bottom
                saved.bottomId?.let { bottomId ->
                    val bottom = ClothingItemsProvider.getAllClothingItems().find { it.id == bottomId }
                    bottom?.let {
                        selectedBottom = it
                        overlayBottom.setImageResource(it.drawableResId)
                        overlayBottom.visibility = View.VISIBLE
                    }
                }
                
                // Restore scale factors
                scarfScale = saved.scarfScale
                shirtScale = saved.shirtScale
                bottomScale = saved.bottomScale
                
                overlayScarf.scaleX = scarfScale
                overlayScarf.scaleY = scarfScale
                overlayShirt.scaleX = shirtScale
                overlayShirt.scaleY = shirtScale
                overlayBottom.scaleX = bottomScale
                overlayBottom.scaleY = bottomScale
                
                // Restore position offsets
                scarfOffsetX = saved.scarfOffsetX
                scarfOffsetY = saved.scarfOffsetY
                shirtOffsetX = saved.shirtOffsetX
                shirtOffsetY = saved.shirtOffsetY
                bottomOffsetX = saved.bottomOffsetX
                bottomOffsetY = saved.bottomOffsetY
                
                overlayScarf.translationX = scarfOffsetX
                overlayScarf.translationY = scarfOffsetY
                overlayShirt.translationX = shirtOffsetX
                overlayShirt.translationY = shirtOffsetY
                overlayBottom.translationX = bottomOffsetX
                overlayBottom.translationY = bottomOffsetY
            }
        }
    }
    
    private fun saveBearCustomization() {
        lifecycleScope.launch {
            val customization = BearCustomizationEntity(
                id = 1,
                scarfId = selectedScarf?.id,
                shirtId = selectedShirt?.id,
                bottomId = selectedBottom?.id,
                scarfScale = scarfScale,
                shirtScale = shirtScale,
                bottomScale = bottomScale,
                scarfOffsetX = scarfOffsetX,
                scarfOffsetY = scarfOffsetY,
                shirtOffsetX = shirtOffsetX,
                shirtOffsetY = shirtOffsetY,
                bottomOffsetX = bottomOffsetX,
                bottomOffsetY = bottomOffsetY
            )
            repository.saveBearCustomization(customization)
            Toast.makeText(requireContext(), "Bear outfit saved!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun resetBearCustomization() {
        lifecycleScope.launch {
            repository.resetBearCustomization()
            
            // Reset UI
            selectedScarf = null
            selectedShirt = null
            selectedBottom = null
            
            overlayScarf.visibility = View.GONE
            overlayShirt.visibility = View.GONE
            overlayBottom.visibility = View.GONE
            
            // Reset scales
            scarfScale = 1.0f
            shirtScale = 1.0f
            bottomScale = 1.0f
            
            overlayScarf.scaleX = 1.0f
            overlayScarf.scaleY = 1.0f
            overlayShirt.scaleX = 1.0f
            overlayShirt.scaleY = 1.0f
            overlayBottom.scaleX = 1.0f
            overlayBottom.scaleY = 1.0f
            
            // Reset positions
            scarfOffsetX = 0f
            scarfOffsetY = 0f
            shirtOffsetX = 0f
            shirtOffsetY = 0f
            bottomOffsetX = 0f
            bottomOffsetY = 0f
            
            overlayScarf.translationX = 0f
            overlayScarf.translationY = 0f
            overlayShirt.translationX = 0f
            overlayShirt.translationY = 0f
            overlayBottom.translationX = 0f
            overlayBottom.translationY = 0f
            
            // Hide adjustment controls
            adjustmentControls.visibility = View.GONE
            selectedOverlay = null
            
            Toast.makeText(requireContext(), "Bear outfit reset!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTabs() {
        tabScarves.setOnClickListener {
            showCategory(ClothingCategory.SCARF)
        }
        
        tabShirts.setOnClickListener {
            showCategory(ClothingCategory.SHIRT)
        }
        
        tabBottoms.setOnClickListener {
            showCategory(ClothingCategory.BOTTOM)
        }
    }
    
    private fun setupOverlayClickListeners() {
        overlayScarf.setOnClickListener {
            if (overlayScarf.visibility == View.VISIBLE) {
                selectOverlayForAdjustment(ClothingCategory.SCARF)
            }
        }
        
        overlayShirt.setOnClickListener {
            if (overlayShirt.visibility == View.VISIBLE) {
                selectOverlayForAdjustment(ClothingCategory.SHIRT)
            }
        }
        
        overlayBottom.setOnClickListener {
            if (overlayBottom.visibility == View.VISIBLE) {
                selectOverlayForAdjustment(ClothingCategory.BOTTOM)
            }
        }
    }
    
    private fun selectOverlayForAdjustment(category: ClothingCategory) {
        selectedOverlay = category
        adjustmentControls.visibility = View.VISIBLE
        
        // Update label to show which item is selected
        adjustmentLabel.text = when (category) {
            ClothingCategory.SCARF -> "Adjust Scarf"
            ClothingCategory.SHIRT -> "Adjust Shirt"
            ClothingCategory.BOTTOM -> "Adjust Bottom"
        }
        
        // Highlight selected overlay
        highlightSelectedOverlay(category)
    }
    
    private fun highlightSelectedOverlay(category: ClothingCategory) {
        // Reset all overlays
        overlayScarf.alpha = if (overlayScarf.visibility == View.VISIBLE) 0.7f else 1.0f
        overlayShirt.alpha = if (overlayShirt.visibility == View.VISIBLE) 0.7f else 1.0f
        overlayBottom.alpha = if (overlayBottom.visibility == View.VISIBLE) 0.7f else 1.0f
        
        // Highlight selected
        when (category) {
            ClothingCategory.SCARF -> overlayScarf.alpha = 1.0f
            ClothingCategory.SHIRT -> overlayShirt.alpha = 1.0f
            ClothingCategory.BOTTOM -> overlayBottom.alpha = 1.0f
        }
    }
    
    private fun setupAdjustmentControls() {
        // Size controls
        btnSizeUp.setOnClickListener {
            selectedOverlay?.let { category ->
                adjustSize(category, 0.1f)
            }
        }
        
        btnSizeDown.setOnClickListener {
            selectedOverlay?.let { category ->
                adjustSize(category, -0.1f)
            }
        }
        
        // Position controls
        btnMoveUp.setOnClickListener {
            selectedOverlay?.let { category ->
                adjustPosition(category, 0f, -MOVE_STEP)
            }
        }
        
        btnMoveDown.setOnClickListener {
            selectedOverlay?.let { category ->
                adjustPosition(category, 0f, MOVE_STEP)
            }
        }
        
        btnMoveLeft.setOnClickListener {
            selectedOverlay?.let { category ->
                adjustPosition(category, -MOVE_STEP, 0f)
            }
        }
        
        btnMoveRight.setOnClickListener {
            selectedOverlay?.let { category ->
                adjustPosition(category, MOVE_STEP, 0f)
            }
        }
    }
    
    private fun adjustSize(category: ClothingCategory, delta: Float) {
        when (category) {
            ClothingCategory.SCARF -> {
                scarfScale = (scarfScale + delta).coerceIn(0.5f, 2.0f)
                overlayScarf.scaleX = scarfScale
                overlayScarf.scaleY = scarfScale
            }
            ClothingCategory.SHIRT -> {
                shirtScale = (shirtScale + delta).coerceIn(0.5f, 2.0f)
                overlayShirt.scaleX = shirtScale
                overlayShirt.scaleY = shirtScale
            }
            ClothingCategory.BOTTOM -> {
                bottomScale = (bottomScale + delta).coerceIn(0.5f, 2.0f)
                overlayBottom.scaleX = bottomScale
                overlayBottom.scaleY = bottomScale
            }
        }
    }
    
    private fun adjustPosition(category: ClothingCategory, deltaX: Float, deltaY: Float) {
        val density = resources.displayMetrics.density
        val deltaXPx = deltaX * density
        val deltaYPx = deltaY * density
        
        when (category) {
            ClothingCategory.SCARF -> {
                scarfOffsetX += deltaXPx
                scarfOffsetY += deltaYPx
                overlayScarf.translationX = scarfOffsetX
                overlayScarf.translationY = scarfOffsetY
            }
            ClothingCategory.SHIRT -> {
                shirtOffsetX += deltaXPx
                shirtOffsetY += deltaYPx
                overlayShirt.translationX = shirtOffsetX
                overlayShirt.translationY = shirtOffsetY
            }
            ClothingCategory.BOTTOM -> {
                bottomOffsetX += deltaXPx
                bottomOffsetY += deltaYPx
                overlayBottom.translationX = bottomOffsetX
                overlayBottom.translationY = bottomOffsetY
            }
        }
    }

    private fun showCategory(category: ClothingCategory) {
        currentCategory = category
        
        // Update tab appearance
        updateTabAppearance()
        
        // Update RecyclerView items
        val items = ClothingItemsProvider.getItemsByCategory(category)
        adapter.updateItems(items)
    }

    private fun updateTabAppearance() {
        // Reset all tabs to unselected state
        tabScarves.setBackgroundResource(R.drawable.bg_tab_unselected)
        tabShirts.setBackgroundResource(R.drawable.bg_tab_unselected)
        tabBottoms.setBackgroundResource(R.drawable.bg_tab_unselected)
        
        // Highlight active tab
        when (currentCategory) {
            ClothingCategory.SCARF -> {
                tabScarves.setBackgroundResource(R.drawable.bg_tab_selected)
            }
            ClothingCategory.SHIRT -> {
                tabShirts.setBackgroundResource(R.drawable.bg_tab_selected)
            }
            ClothingCategory.BOTTOM -> {
                tabBottoms.setBackgroundResource(R.drawable.bg_tab_selected)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = ClothingAdapter(emptyList()) { item ->
            // On click, apply the clothing item directly
            applyClothingItem(item)
        }
        
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter
    }

    private fun setupDragAndDrop() {
        // Set up the bear container as a drop target
        bearContainer.setOnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    // Accept the drag
                    true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    // Visual feedback when dragging over
                    view.alpha = 0.8f
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    // Reset visual feedback
                    view.alpha = 1.0f
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // Get the dropped item
                    val item = event.localState as? ClothingItem
                    item?.let {
                        applyClothingItem(it)
                    }
                    view.alpha = 1.0f
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    view.alpha = 1.0f
                    true
                }
                else -> false
            }
        }
    }

    private fun applyClothingItem(item: ClothingItem) {
        when (item.category) {
            ClothingCategory.SCARF -> {
                selectedScarf = item
                overlayScarf.setImageResource(item.drawableResId)
                overlayScarf.visibility = View.VISIBLE
            }
            ClothingCategory.SHIRT -> {
                selectedShirt = item
                overlayShirt.setImageResource(item.drawableResId)
                overlayShirt.visibility = View.VISIBLE
            }
            ClothingCategory.BOTTOM -> {
                selectedBottom = item
                overlayBottom.setImageResource(item.drawableResId)
                overlayBottom.visibility = View.VISIBLE
            }
        }
    }

    // Method to remove clothing item (can be called with double-tap or button)
    private fun removeClothingItem(category: ClothingCategory) {
        when (category) {
            ClothingCategory.SCARF -> {
                selectedScarf = null
                overlayScarf.visibility = View.GONE
            }
            ClothingCategory.SHIRT -> {
                selectedShirt = null
                overlayShirt.visibility = View.GONE
            }
            ClothingCategory.BOTTOM -> {
                selectedBottom = null
                overlayBottom.visibility = View.GONE
            }
        }
    }
}
