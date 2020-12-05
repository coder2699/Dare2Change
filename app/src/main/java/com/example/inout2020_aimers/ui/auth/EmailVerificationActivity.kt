package com.example.inout2020_aimers.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentEmailVerificationActivityBinding
import com.example.inout2020_aimers.ui.HomeActivity
import com.example.inout2020_aimers.utils.Snacker
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch


class EmailVerificationActivity : Fragment(R.layout.fragment_email_verification_activity) {

    private val TAG = "EmailVerificationActivity"
    private lateinit var auth : FirebaseAuth
    private lateinit var binding:FragmentEmailVerificationActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = auth.currentUser
        val userMail = user!!.email

        binding = FragmentEmailVerificationActivityBinding.bind(view)
        binding.tvMail.text = userMail


        // Continue button
        binding.btnContinue.setOnClickListener {

            setLoading()

            user!!.reload().addOnSuccessListener {
                // Checking if user has verified email
                if (user!!.isEmailVerified){

                    Log.d(TAG, "onViewCreated: User has verified email")

                    // Navigating to HomeActivity
                    Intent(requireContext(), HomeActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }

                }else{
                    unSetLaoding()
                    Snacker(view,"Please verify mail to continue").error()
                }
            }

        }

        // Resend button
        binding.btnResendMail.setOnClickListener {

            setLoading()

            if (user?.isEmailVerified == false){
                user.sendEmailVerification().addOnSuccessListener {
                    Snacker(view,"Verification mail sent").success()
                    unSetLaoding()
                }
                    .addOnFailureListener {
                        Snacker(view,"Something went wrong").error()
                        unSetLaoding()
                    }
            }


        }

    }

    private fun setLoading(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun unSetLaoding(){
        binding.progressBar.visibility = View.GONE
    }



}