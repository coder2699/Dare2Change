package com.example.inout2020_aimers.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.ActivityHomeBinding
import com.example.inout2020_aimers.databinding.FragmentAuthOptionsBinding
import com.example.inout2020_aimers.ui.HomeActivity
import com.google.firebase.auth.FirebaseAuth


class AuthOptionsFragment : Fragment(R.layout.fragment_auth_options) {
    private  val TAG = "AuthOptionsFragment"
    private lateinit var binding: FragmentAuthOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()

        // Checking if user has signed in
        if (auth.currentUser != null){

            if (auth.currentUser!!.isEmailVerified){
                // Navigating to HomeActivity
                Intent(requireContext(), HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{

                findNavController().navigate(R.id.action_authOptionsFragment_to_emailVerificationActivity)
            }


        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAuthOptionsBinding.bind(view)

        // Navigate to LogIn Fragment
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_authOptionsFragment_to_loginFragment)
        }

        // Navigate to Sign Up Fragment
        binding.btnSignupEmail.setOnClickListener {
            findNavController().navigate(R.id.action_authOptionsFragment_to_signupFragment)
        }



    }

}