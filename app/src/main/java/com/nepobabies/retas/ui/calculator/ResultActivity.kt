package com.nepobabies.retas.ui.calculator

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nepobabies.retas.R
import com.nepobabies.retas.utils.FootprintCalculator

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setupBackButton()
        calculateAndDisplayResult()
    }

    private fun setupBackButton() {
        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
    }

    private fun calculateAndDisplayResult() {
        val frequency = intent.getStringExtra("frequency") ?: "Per Month"
        val tops = intent.getIntExtra("tops", 0)
        val bottoms = intent.getIntExtra("bottoms", 0)
        val outerwear = intent.getIntExtra("outerwear", 0)
        val dress = intent.getIntExtra("dress", 0)

        val score = FootprintCalculator.calculateScore(frequency, tops, bottoms, outerwear, dress)
        val footprint = FootprintCalculator.classifyFootprint(score)

        val resultLabel = findViewById<TextView>(R.id.text_result_value)
        val resultMessage = findViewById<TextView>(R.id.text_result_message)
        val progressBar = findViewById<ProgressBar>(R.id.progress_result)

        resultLabel.text = footprint
        progressBar.progress = score.coerceIn(0, 100)

        resultMessage.text = when (footprint) {
            "Low" -> "Awesome! You're a mindful shopper."
            "Medium" -> "Great! That's likely you."
            "High" -> "Let's explore ways to reduce your impact."
            else -> ""
        }
    }
}
