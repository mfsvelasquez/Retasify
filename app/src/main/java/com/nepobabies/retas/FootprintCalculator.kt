package com.nepobabies.retas

object FootprintCalculator {

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

    fun classifyFootprint(score: Int): String {
        return when {
            score < 200 -> "Low"
            score < 600 -> "Medium"
            else -> "High"
        }
    }
}
