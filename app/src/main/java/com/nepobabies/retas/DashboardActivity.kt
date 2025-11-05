package com.nepobabies.retasify

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.nepobabies.retas.R


class DashboardActivity : AppCompatActivity() {
    private lateinit var userNameTextView: TextView
    private lateinit var fashionFootprintCard: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)

        userNameTextView = findViewById(R.id.user_name_text)
        fashionFootprintCard = findViewById(R.id.fashion_footprint_card)

        loadAndDisplayUserName()

        fashionFootprintCard.setOnClickListener {
            Toast.makeText(this, "Fashion Footprint card clicked!", Toast.LENGTH_SHORT).show()
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
