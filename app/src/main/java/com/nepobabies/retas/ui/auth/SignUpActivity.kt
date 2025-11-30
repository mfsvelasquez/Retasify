package com.nepobabies.retas.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.main.MainActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signUpButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        signUpButton = findViewById(R.id.signUpButton)
    }

    private fun setupClickListeners() {
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (validateInput(email, password, confirmPassword)) {
                // TODO: Replace with Firebase Authentication
                performSignUp(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty()) {
            emailEditText.error = "Email is required"
            return false
        }
        if (password.isEmpty()) {
            passwordEditText.error = "Password is required"
            return false
        }
        if (password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters"
            return false
        }
        if (password != confirmPassword) {
            confirmPasswordEditText.error = "Passwords do not match"
            return false
        }
        return true
    }

    private fun performSignUp(email: String, password: String) {
        // TODO: Implement Firebase Authentication here
        // For now, placeholder navigation to MainActivity
        Toast.makeText(this, "Sign up successful (placeholder)", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
