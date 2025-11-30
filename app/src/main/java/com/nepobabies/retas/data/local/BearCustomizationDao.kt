package com.nepobabies.retas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object for bear customization operations.
 */
@Dao
interface BearCustomizationDao {
    
    @Query("SELECT * FROM bear_customization WHERE id = 1")
    suspend fun getBearCustomization(): BearCustomizationEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBearCustomization(customization: BearCustomizationEntity)
    
    @Query("DELETE FROM bear_customization WHERE id = 1")
    suspend fun resetBearCustomization()
}
