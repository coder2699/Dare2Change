package com.example.inout2020_aimers.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.FragmentAuthOptionsBinding
import com.example.inout2020_aimers.ui.HomeActivity
import com.example.inout2020_aimers.utils.SecretKeys.Companion.GOOGLE_WEB_ID
import com.example.inout2020_aimers.utils.Snacker
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthOptionsFragment : Fragment(R.layout.fragment_auth_options) {
    private  val TAG = "AuthOptionsFragment"
    private lateinit var binding: FragmentAuthOptionsBinding
    private lateinit var auth:FirebaseAuth
    private val GOOGLE_SIGNIN_REQUEST_CODE = 11

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

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

        // Google SignUp
        binding.btnGoogleSignin.setOnClickListener {

            val googleOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(GOOGLE_WEB_ID)
                .requestEmail()
                .build()

            val googleSigninClient = GoogleSignIn.getClient(requireContext(),googleOptions)

            googleSigninClient.signInIntent.also {
                startActivityForResult(it,GOOGLE_SIGNIN_REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGNIN_REQUEST_CODE){
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result

            account?.let {
                authGoogleforFirebase(it)
            }
        }
    }

    private fun authGoogleforFirebase(account: GoogleSignInAccount?) {

        val cred = GoogleAuthProvider.getCredential(account?.idToken,null)

        auth.signInWithCredential(cred)
            .addOnSuccessListener {
                 // Google Signin Success
                // Navigating to HomeActivity
                Intent(requireContext(), HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "authGoogleforFirebase: Google SignUp failed")
                Snacker(requireView(),"Error while Google Sign Up")
            }


    }

}