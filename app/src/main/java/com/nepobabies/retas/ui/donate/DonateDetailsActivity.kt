package com.nepobabies.retas.ui.donate

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nepobabies.retas.R

class DonateDetailsActivity : AppCompatActivity() {
    
    private var condition: String = "good"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_donate_details)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get condition from intent
        condition = intent.getStringExtra("condition") ?: "good"

        setupUI()
    }

    private fun setupUI() {
        // TODO: Setup donation form fields
        // - Clothing type selection
        // - Photo upload  
        // - Description
        // - Pickup/dropoff preference
        // - Add a submit button to the layout (R.id.submitButton)
        
        // Note: Submit button needs to be added to activity_donate_details.xml
        // For now, user can navigate back using system back button
    }
}
