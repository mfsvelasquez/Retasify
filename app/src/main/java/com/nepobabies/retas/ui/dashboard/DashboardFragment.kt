package com.nepobabies.retas.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.nepobabies.retas.R
import com.nepobabies.retas.ui.calculator.FashionCalculatorActivity
import com.squareup.picasso.Picasso

class DashboardFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var profileID: String
    private lateinit var firebaseUser: FirebaseUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        profileID = firebaseUser.uid

        // Setup user greeting
        val userNameText = view.findViewById<TextView>(R.id.user_name_text)
        val profilePicture = view.findViewById<ShapeableImageView>(R.id.profile_image)

        db.collection("profile").document(profileID)
            .addSnapshotListener { snap, except ->
                var username = snap?.getString("name").toString().split(" ")[0]
                // TODO: Get user name from Firebase/SharedPreferences
                userNameText?.text = "$username!"
                Picasso.get().load(snap?.getString("image").toString()).into(profilePicture)
            }

        // Setup Fashion Footprint card click
        val fashionFootprintCard = view.findViewById<MaterialCardView>(R.id.fashion_footprint_card)
        fashionFootprintCard?.setOnClickListener {
            startActivity(Intent(requireContext(), FashionCalculatorActivity::class.java))
        }
    }
}
