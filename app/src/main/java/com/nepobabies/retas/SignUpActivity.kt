package com.nepobabies.retas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nepobabies.retas.DashboardFragment
import com.nepobabies.retas.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val backButton: ImageButton = findViewById(R.id.backButton)
        val alreadyHaveAccountTextView: TextView = findViewById(R.id.alreadyHaveAccountTextView)
        val signUpButton: Button = findViewById(R.id.signUpButton) // Make sure this ID matches your XML

        backButton.setOnClickListener {
            finish()
        }

        alreadyHaveAccountTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            // TODO: add your authentication logic here (e.g., check username/password)
            // After successful login:
            val intent = Intent(this, MainActivity::class.java)

            // Optional: clear back stack so user can't go back to LoginActivity
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
