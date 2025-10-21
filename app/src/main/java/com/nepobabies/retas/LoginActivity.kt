package com.nepobabies.retas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.button.MaterialButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nepobabies.retas.DashboardFragment
import com.nepobabies.retas.R
import android.widget.Toast
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 🔙 Back button logic
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // ✅ Login button logic
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        loginButton.setOnClickListener {
            // TODO: add your authentication logic here (e.g., check username/password)
            // After successful login:
            val intent = Intent(this, MainActivity::class.java)

            // Optional: clear back stack so user can't go back to LoginActivity
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
            val text = "Incorrect Password or Email"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }




        // 🧢 Handle system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
