package com.nepobabies.retas.data.model

/**
 * Represents a user in the app.
 * TODO: This model will be used with Firebase Firestore.
 */
data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val profileImageUrl: String? = null,
    val donationCount: Int = 0,
    val totalItemsDonated: Int = 0,
    val achievements: List<String> = emptyList(),
    val bearClothingUnlocked: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)
