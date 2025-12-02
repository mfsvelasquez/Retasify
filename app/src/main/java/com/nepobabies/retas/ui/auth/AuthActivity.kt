package com.nepobabies.retas.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.main.MainActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        auth = Firebase.auth

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Signing in ...")
        progressDialog.setCancelable(false)

        val loginButton: MaterialButton = findViewById(R.id.loginButton)
        val signUpButton: MaterialButton = findViewById(R.id.signUpButton)

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        progressDialog.show()
        if (currentUser == null) {
            progressDialog.dismiss()
            return
        } else {
            progressDialog.dismiss()
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Sign in Successful", Toast.LENGTH_SHORT).show()
        }
    }
}
