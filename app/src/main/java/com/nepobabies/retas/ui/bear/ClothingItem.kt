package com.nepobabies.retas.ui.bear

import com.nepobabies.retas.R

/**
 * Represents a clothing item for the bear customization feature.
 */
data class ClothingItem(
    val id: String,
    val name: String,
    val category: ClothingCategory,
    val drawableResId: Int,
    val isUnlocked: Boolean = true
)

enum class ClothingCategory(val displayName: String) {
    SCARF("Scarves"),
    SHIRT("Shirts"),
    BOTTOM("Bottoms")
}

/**
 * Provides the available clothing items for bear customization.
 */
object ClothingItemsProvider {
    
    fun getAllClothingItems(): List<ClothingItem> {
        return listOf(
            // Scarves
            ClothingItem("scarf_green", "Green Scarf", ClothingCategory.SCARF, R.drawable.green_scarf),
            ClothingItem("scarf_pink", "Pink Scarf", ClothingCategory.SCARF, R.drawable.pink_scarf),
            
            // Shirts
            ClothingItem("shirt_green", "Green Shirt", ClothingCategory.SHIRT, R.drawable.green_shirt),
            ClothingItem("shirt_light", "Light Shirt", ClothingCategory.SHIRT, R.drawable.light_shirt),
            
            // Bottoms
            ClothingItem("short_green", "Green Shorts", ClothingCategory.BOTTOM, R.drawable.green_short),
            ClothingItem("skirt_light", "Light Skirt", ClothingCategory.BOTTOM, R.drawable.light_skirt)
        )
    }
    
    fun getItemsByCategory(category: ClothingCategory): List<ClothingItem> {
        return getAllClothingItems().filter { it.category == category }
    }
    
    fun getScarves(): List<ClothingItem> = getItemsByCategory(ClothingCategory.SCARF)
    fun getShirts(): List<ClothingItem> = getItemsByCategory(ClothingCategory.SHIRT)
    fun getBottoms(): List<ClothingItem> = getItemsByCategory(ClothingCategory.BOTTOM)
}
