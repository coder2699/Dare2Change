package com.example.inout2020_aimers.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentSignupBinding
import com.example.inout2020_aimers.ui.HomeActivity
import com.example.inout2020_aimers.utils.Snacker
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private lateinit var binding : FragmentSignupBinding
    private lateinit var auth : FirebaseAuth
    private val TAG = "SignupFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignupBinding.bind(view)

        binding.btnSignUpUser.setOnClickListener {

            Log.d(TAG, "onViewCreated: Cliked")

            val userName = binding.etUsernameSignUp.text.toString()
            val email = binding.etEmailSignUp.text.toString()
            val password = binding.etPasswordSignUp.text.toString()

            if (userName.isEmpty()){
                binding.etUsernameSignUp.error = "Username required"
            }else if (email.isEmpty()){
                binding.etEmailSignUp.error = "Email required"
            }else if(password.isEmpty()){
                binding.etPasswordSignUp.error = "Password required"
            }else if (password.length < 8){
                binding.etPasswordSignUp.error = "Minimum 8 charcters required"
            }else{
                Log.d(TAG, "onViewCreated: Sign up form validated")

                // Loading UI
                setLoading()

                // Signing up user
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        Log.d(TAG, "onViewCreated: Signing up user -> SUCCESS")
                        Snacker(view,"Account created successfully").success()

                        auth.currentUser!!.sendEmailVerification()
                            .addOnSuccessListener {
                                Log.d(TAG, "onViewCreated: Verification mail sent")
                            }.addOnFailureListener {
                                Log.d(TAG, "onViewCreated: Failed to send verification email")
                            }

                        findNavController().navigate(R.id.action_signupFragment_to_emailVerificationActivity)


                    }
                    .addOnFailureListener {
                        Log.d(TAG, "onViewCreated: Signing up used - > FAILED")
                        Log.d(TAG, "onViewCreated: FAILURE MSG -> ${it.message}")
                        Snacker(view,"${it.message}")

                        // Loading UI stopped
                        UnSetLoading()
                    }
                    .addOnCompleteListener {
                        Log.d(TAG, "onViewCreated: COMPLETED :)")
                    }


            }

        }



    }

    fun setLoading(){
        binding.layout.visibility = View.INVISIBLE
        binding.loadingLayout.visibility = View.VISIBLE
    }

    fun UnSetLoading(){
        binding.layout.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.INVISIBLE
    }

}