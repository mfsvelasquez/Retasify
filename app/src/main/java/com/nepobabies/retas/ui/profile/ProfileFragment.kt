package com.nepobabies.retas.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.animation.core.snap
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.auth.LoginActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var profileID: String
    private lateinit var firebaseUser: FirebaseUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        profileID = firebaseUser.uid


        setupProfileUI(view)
    }

    private fun setupProfileUI(view: View) {
        // Setup profile data with existing views
        val profileTitle = view.findViewById<TextView>(R.id.profile_title)
        val textView3 = view.findViewById<TextView>(R.id.textView3)
        val profile_image = view.findViewById<ShapeableImageView>(R.id.profile_image)

        // TODO: Load user profile from Firebase
        // - Profile picture
        // - Username

        db.collection("profile").document(profileID)
            .addSnapshotListener { snap, except ->

                // TODO: Set actual user name from Firebase
                profileTitle?.text = snap?.getString("name").toString()
                textView3?.text = snap?.getString("email").toString()
                Picasso.get().load(snap?.getString("image").toString()).into(profile_image)
            }




        // - Donation stats
        // - Achievements/badges
        // - Settings
        // - Add logout button to layout (R.id.logoutButton)
        val logoutButton = view.findViewById<MaterialButton>(R.id.signOutButton)
        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
        }
    }
}
