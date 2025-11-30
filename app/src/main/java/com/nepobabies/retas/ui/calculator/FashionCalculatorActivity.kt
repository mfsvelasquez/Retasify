package com.nepobabies.retas.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.nepobabies.retas.R

class FashionCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        setupBackButton()
        setupUI()
    }

    private fun setupBackButton() {
        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            finish()
        }
    }

    private fun setupUI() {
        val frequencyDropdown = findViewById<Spinner>(R.id.spinner_frequency)
        val calculateButton = findViewById<Button>(R.id.button_calculate)

        val topsSlider = findViewById<Slider>(R.id.slider_tops)
        val bottomsSlider = findViewById<Slider>(R.id.slider_bottoms)
        val outerwearSlider = findViewById<Slider>(R.id.slider_outerwear)
        val dressSlider = findViewById<Slider>(R.id.slider_dress)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.frequency_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        frequencyDropdown.adapter = adapter

        calculateButton.setOnClickListener {
            val frequency = frequencyDropdown.selectedItem.toString()
            val tops = topsSlider.value.toInt()
            val bottoms = bottomsSlider.value.toInt()
            val outerwear = outerwearSlider.value.toInt()
            val dress = dressSlider.value.toInt()

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("frequency", frequency)
                putExtra("tops", tops)
                putExtra("bottoms", bottoms)
                putExtra("outerwear", outerwear)
                putExtra("dress", dress)
            }
            startActivity(intent)
        }
    }
}
