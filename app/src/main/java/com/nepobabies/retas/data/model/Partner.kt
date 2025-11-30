package com.nepobabies.retas.data.model

/**
 * Represents an upcycling partner/service.
 * TODO: This model will be used with Firebase Firestore.
 */
data class Partner(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val logoUrl: String? = null,
    val services: List<String> = emptyList(), // e.g., ["alterations", "repairs", "upcycling"]
    val address: String = "",
    val contactPhone: String = "",
    val contactEmail: String = "",
    val rating: Float = 0f,
    val isActive: Boolean = true
)
