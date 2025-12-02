package com.nepobabies.retas.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toUpperCase
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.main.MainActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signUpButton: MaterialButton
    private lateinit var backButton: ImageButton
    private lateinit var fullNameEditText: EditText

    private lateinit var progressDialog: ProgressDialog

    private lateinit var auth: FirebaseAuth;
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initViews()
        setupClickListeners()

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account ...")
        progressDialog.setCancelable(false)
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        signUpButton = findViewById(R.id.signUpButton)
        backButton = findViewById(R.id.backButton)
        fullNameEditText = findViewById(R.id.nameEditText)
    }

    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            val name = fullNameEditText.text.toString().trim()

            if (validateInput(email, password, confirmPassword, name)) {
                // TODO: Replace with Firebase Authentication
                performSignUp(email, password, name)
            }
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String, name: String): Boolean {
        if (name.isEmpty()) {
            fullNameEditText.error = "Full Name is required"
            return false
        }
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

    private fun performSignUp(email: String, password: String, name: String) {
        progressDialog.show()

        // TODO: Implement Firebase Authentication here
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {

                    val currentUser = auth.currentUser!!.uid

                    val userMap = HashMap<String, Any>()
                    userMap["uid"] = currentUser
                    userMap["name"] = name.toUpperCase()
                    userMap["email"] = email
                    userMap["image"] = "https://res.cloudinary.com/daowluvai/image/upload/v1764688647/smiling-woman-with-glasses_1308-177859_k9ev4u.png"

                    db.collection("profile")
                        .document(currentUser)
                        .set(userMap)

                    progressDialog.dismiss()

                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            }

    }


}
