package com.example.inout2020_aimers.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.MainActivity
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.bucket.BucketList.DisplayFragmentDirections
import com.example.inout2020_aimers.databinding.ActivityAuthBinding.inflate
import com.example.inout2020_aimers.databinding.FragmentDashboardBinding
import com.example.inout2020_aimers.ui.auth.AuthActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val TAG = "DashboardFragment"
    private lateinit var auth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPref:SharedPreferences.Editor
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
        binding.toolbarDashboard.inflateMenu(R.menu.dashboard_vert_dot)
        binding.toolbarDashboard.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
        val appPref:SharedPreferences
        appPref=context?.getSharedPreferences("AppSettings",Context.MODE_PRIVATE)!!
         sharedPref=appPref.edit()
        val isNightModeOn:Boolean=appPref.getBoolean("NightMode",false)
        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Light->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPref.putBoolean("NightMode",false)
                sharedPref.apply()
            }
            R.id.Dark->{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPref.putBoolean("NightMode",true)
                sharedPref.apply()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}