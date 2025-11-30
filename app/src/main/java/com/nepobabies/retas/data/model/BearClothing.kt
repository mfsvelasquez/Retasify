package com.nepobabies.retas.data.model

/**
 * Represents a clothing item for the virtual bear.
 * TODO: This model will be used with Firebase Firestore.
 */
data class BearClothing(
    val id: String = "",
    val name: String = "",
    val category: String = "", // "top", "bottom", "accessory", "hat", "shoes"
    val imageResourceName: String = "", // Reference to drawable resource
    val requiredDonations: Int = 0, // Number of donations needed to unlock
    val isDefault: Boolean = false // Whether it's available from start
)
