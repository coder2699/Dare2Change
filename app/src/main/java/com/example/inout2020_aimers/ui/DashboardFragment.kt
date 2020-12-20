package com.example.inout2020_aimers.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentDashboardBinding
import com.example.inout2020_aimers.ui.auth.AuthActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val TAG = "DashboardFragment"
    private lateinit var auth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = context?.getSharedPreferences("FIRST_RUN", Context.MODE_PRIVATE)!!
        val editor = sharedPreferences.edit()
        editor?.putInt("FIRST_RUN", 1)
        editor?.apply()

        binding = FragmentDashboardBinding.bind(view)

        auth = FirebaseAuth.getInstance()

        binding.btnBucketlist.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_displayFragment)
        }

        binding.btnMilestone.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_startFragment)
        }

        binding.btnMotivation.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_motivationFragment)
        }

        binding.btnExercise.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_startExerciseFragment)
        }


        binding.btnProTips.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_proTipsActivity)
        }

        binding.btnSoftMusic.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_musicPlayerFragment)
        }

        binding.toolbarDashboard.setOnMenuItemClickListener { menuItem ->

            Log.d(TAG, "onViewCreated: Menu clicked ")
            if (menuItem.itemId == R.id.signOut) {
                Log.d(TAG, "onViewCreated: Signout menu item clikced")

                auth.signOut()

                // Navigating to AuthActivity
                Intent(requireContext(), AuthActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }


            } else if (menuItem.itemId == R.id.settings) {
                Log.d(TAG, "onViewCreated: Settings menu item clicked ")
            }

            return@setOnMenuItemClickListener true

        }

    }


}