package com.nepobabies.retas.data.model

/**
 * Represents a clothing donation.
 * TODO: This model will be used with Firebase Firestore.
 */
data class Donation(
    val id: String = "",
    val userId: String = "",
    val condition: String = "", // "great", "good", "okay", "bad"
    val clothingType: String = "", // "tops", "bottoms", "outerwear", "dress", "accessories"
    val description: String = "",
    val imageUrl: String? = null,
    val pickupPreference: String = "dropoff", // "pickup" or "dropoff"
    val status: String = "pending", // "pending", "scheduled", "completed"
    val createdAt: Long = System.currentTimeMillis()
)
