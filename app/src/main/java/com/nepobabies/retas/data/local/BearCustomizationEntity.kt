package com.nepobabies.retas.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing the saved bear customization state.
 * Only one row will exist in this table (id = 1) representing the current bear state.
 */
@Entity(tableName = "bear_customization")
data class BearCustomizationEntity(
    @PrimaryKey
    val id: Int = 1,
    
    // Selected clothing item IDs (null if not wearing anything)
    val scarfId: String? = null,
    val shirtId: String? = null,
    val bottomId: String? = null,
    
    // Scale factors
    val scarfScale: Float = 1.0f,
    val shirtScale: Float = 1.0f,
    val bottomScale: Float = 1.0f,
    
    // Position offsets
    val scarfOffsetX: Float = 0f,
    val scarfOffsetY: Float = 0f,
    val shirtOffsetX: Float = 0f,
    val shirtOffsetY: Float = 0f,
    val bottomOffsetX: Float = 0f,
    val bottomOffsetY: Float = 0f,
    
    // Bear name
    val bearName: String = "Retaso"
)
