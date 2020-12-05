package com.example.inout2020_aimers.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentLoginBinding
import com.example.inout2020_aimers.ui.HomeActivity
import com.example.inout2020_aimers.utils.Snacker
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login){

    private val TAG = "LoginFragment"
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: FragmentLoginBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.bind(view)

        // Login Button Clicked
        binding.btnLoginUser.setOnClickListener {

            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()

            if (email.isEmpty()){
                binding.etEmailLogin.error = "Email required"
            }else if (password.isEmpty()){
                binding.etPasswordLogin.error = "Password required"
            }else{

                // Loading Screen
                    setLoading()

                // Logging in User
                auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {

                        // Login Success
                        // Navigating to HomeActivity
                        Intent(requireContext(), HomeActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }
                    }
                    .addOnFailureListener {

                        unSetLoading()

                        // Login failed
                        Log.d(TAG, "onViewCreated: Login FAILDED -> ${it.message}")
                        Snacker(view,it.message!!).error()
                    }

            }



        }

        // Forgot Password
        binding.btnForgotPassword.setOnClickListener {

            val email = binding.etEmailLogin.text.toString()

            if (email.isEmpty()){
                binding.etEmailLogin.error = "Email required to reset password"
            }else{

                setLoading()

                auth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        // Password reset email sent successfully
                        unSetLoading()

                        Snacker(view,"Password reset mail has sent to your mail") // This isn't working
                        Toast.makeText(requireContext(),"Password reset mail sent",Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "onViewCreated: SEnt!!!!")
                    }
                    .addOnFailureListener {
                        unSetLoading()
                        Snacker(view,it.message!!).error()
                    }


            }


        }


    }

    fun setLoading(){
        binding.loading.visibility = View.VISIBLE
    }

    fun unSetLoading(){
        binding.loading.visibility = View.GONE
    }



}