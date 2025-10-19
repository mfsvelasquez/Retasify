package com.nepobabies.retas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import androidx.gridlayout.widget.GridLayout

class UpcycleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onLogoClickListener = View.OnClickListener {
            findNavController().navigate(R.id.action_upcycleFragment_to_partnerDetailFragment)
        }

        val communitiesGrid = view.findViewById<GridLayout>(R.id.grid_local_communities)
        val companiesGrid = view.findViewById<GridLayout>(R.id.grid_companies)

        for (child in communitiesGrid.children) {
            if (child is ImageView) {

                child.setOnClickListener(onLogoClickListener)
            }
        }
        for (child in companiesGrid.children) {
            if (child is ImageView) {
                child.setOnClickListener(onLogoClickListener)
            }
        }
    }
}
