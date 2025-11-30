package com.nepobabies.retas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database for the Retasify app.
 */
@Database(
    entities = [BearCustomizationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RetasifyDatabase : RoomDatabase() {
    
    abstract fun bearCustomizationDao(): BearCustomizationDao
    
    companion object {
        @Volatile
        private var INSTANCE: RetasifyDatabase? = null
        
        fun getInstance(context: Context): RetasifyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RetasifyDatabase::class.java,
                    "retasify_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
