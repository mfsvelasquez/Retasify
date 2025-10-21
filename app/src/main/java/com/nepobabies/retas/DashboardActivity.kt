package com.nepobabies.retasify

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.nepobabies.retas.R
import android.content.Intent


class DashboardActivity : AppCompatActivity() {
    private lateinit var userNameTextView: TextView
    private lateinit var fashionFootprintCard: MaterialCardView
    private lateinit var knowFootprintButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)

        userNameTextView = findViewById(R.id.user_name_text)
        fashionFootprintCard = findViewById(R.id.fashion_footprint_card)
        knowFootprintButton = findViewById(R.id.btnKnowFootprint)

        loadAndDisplayUserName()

        knowFootprintButton.setOnClickListener {
            val intent = Intent(this, knowFootprintButton::class.java)
            startActivity(intent)
        }
    }

    private fun loadAndDisplayUserName() {
        val currentUserName = "Lucci"
        userNameTextView.text = "$currentUserName!"
    }

    fun onUserNameChanged(newName: String) {
        userNameTextView.text = "$newName!"
    }
}
