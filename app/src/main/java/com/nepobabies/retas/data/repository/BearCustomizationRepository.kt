package com.nepobabies.retas.data.repository

import android.content.Context
import com.nepobabies.retas.data.local.BearCustomizationEntity
import com.nepobabies.retas.data.local.RetasifyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for managing bear customization data.
 */
class BearCustomizationRepository(context: Context) {
    
    private val database = RetasifyDatabase.getInstance(context)
    private val dao = database.bearCustomizationDao()
    
    /**
     * Get the current bear customization state.
     * Returns null if no customization has been saved yet.
     */
    suspend fun getBearCustomization(): BearCustomizationEntity? {
        return withContext(Dispatchers.IO) {
            dao.getBearCustomization()
        }
    }
    
    /**
     * Save the current bear customization state.
     */
    suspend fun saveBearCustomization(customization: BearCustomizationEntity) {
        withContext(Dispatchers.IO) {
            dao.saveBearCustomization(customization)
        }
    }
    
    /**
     * Reset the bear customization to default state.
     */
    suspend fun resetBearCustomization() {
        withContext(Dispatchers.IO) {
            dao.resetBearCustomization()
        }
    }
    
    companion object {
        @Volatile
        private var INSTANCE: BearCustomizationRepository? = null
        
        fun getInstance(context: Context): BearCustomizationRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = BearCustomizationRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
