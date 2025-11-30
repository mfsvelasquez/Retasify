package com.nepobabies.retas.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Manages local data storage using SharedPreferences.
 * TODO: This will be replaced/supplemented by Firebase when integrated.
 */
object PreferencesManager {
    
    private const val PREFS_NAME = "retasify_prefs"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_USER_EMAIL = "user_email"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_DONATION_COUNT = "donation_count"
    private const val KEY_BEAR_STATE = "bear_state"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    // User session management (placeholder for Firebase Auth)
    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun saveUserName(context: Context, name: String) {
        getPrefs(context).edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getUserName(context: Context): String {
        return getPrefs(context).getString(KEY_USER_NAME, "User") ?: "User"
    }

    fun saveUserEmail(context: Context, email: String) {
        getPrefs(context).edit().putString(KEY_USER_EMAIL, email).apply()
    }

    fun getUserEmail(context: Context): String? {
        return getPrefs(context).getString(KEY_USER_EMAIL, null)
    }

    // Donation tracking
    fun incrementDonationCount(context: Context) {
        val current = getDonationCount(context)
        getPrefs(context).edit().putInt(KEY_DONATION_COUNT, current + 1).apply()
    }

    fun getDonationCount(context: Context): Int {
        return getPrefs(context).getInt(KEY_DONATION_COUNT, 0)
    }

    // Bear customization state
    fun saveBearState(context: Context, stateJson: String) {
        getPrefs(context).edit().putString(KEY_BEAR_STATE, stateJson).apply()
    }

    fun getBearState(context: Context): String? {
        return getPrefs(context).getString(KEY_BEAR_STATE, null)
    }

    fun clearAll(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
