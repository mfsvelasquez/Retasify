package com.nepobabies.retas.utils

/**
 * Calculates fashion footprint score based on clothing purchase habits.
 * Used by the Fashion Calculator feature.
 */
object FootprintCalculator {

    /**
     * Calculate the footprint score based on purchase frequency and quantity.
     * 
     * @param frequency Purchase frequency: "Per Week", "Per Month", or "Per Year"
     * @param tops Number of tops purchased
     * @param bottoms Number of bottoms purchased
     * @param outerwear Number of outerwear items purchased
     * @param dress Number of dresses purchased
     * @return Score from 0-1000
     */
    fun calculateScore(
        frequency: String,
        tops: Int,
        bottoms: Int,
        outerwear: Int,
        dress: Int
    ): Int {
        val totalItems = tops + bottoms + outerwear + dress
        val multiplier = when (frequency) {
            "Per Week" -> 52
            "Per Month" -> 12
            "Per Year" -> 1
            else -> 1
        }
        return (totalItems * multiplier).coerceAtMost(1000)
    }

    /**
     * Classify the footprint level based on score.
     * 
     * @param score The calculated footprint score
     * @return "Low", "Medium", or "High"
     */
    fun classifyFootprint(score: Int): String {
        return when {
            score < 200 -> "Low"
            score < 600 -> "Medium"
            else -> "High"
        }
    }
}
