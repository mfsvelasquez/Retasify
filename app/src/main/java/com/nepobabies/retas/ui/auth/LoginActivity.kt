package com.nepobabies.retas.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var cl: ConstraintLayout

    private lateinit var progressDialog: ProgressDialog

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        setupClickListeners()

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Signing in ...")
        progressDialog.setCancelable(false)
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        backButton = findViewById(R.id.backButton)
        cl = findViewById(R.id.main)
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInput(email, password)) {
                // TODO: Replace with Firebase Authentication
                performLogin(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            emailEditText.error = "Email is required"
            return false
        }
        if (password.isEmpty()) {
            passwordEditText.error = "Password is required"
            return false
        }
        return true
    }

    private fun performLogin(email: String, password: String) {
        // TODO: Implement Firebase Authentication here
        progressDialog.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    val email = firebaseUser!!.email
                    Toast.makeText(this, "$email successfully logged in", Toast.LENGTH_SHORT).show()
                    updateUI(firebaseUser)
                    progressDialog.dismiss()
                } else {
                    progressDialog.dismiss()
                    Snackbar.make(cl, "Incorrect Email/Password", Snackbar.LENGTH_SHORT).show()
                }
            }

        Toast.makeText(this, "Login successful (placeholder)", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onStart () {
        super.onStart()

        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser == null) {
            return
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Sign in Successful", Toast.LENGTH_SHORT).show()
        }
    }
}
