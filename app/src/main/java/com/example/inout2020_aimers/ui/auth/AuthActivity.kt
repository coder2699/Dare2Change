package com.example.inout2020_aimers.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inout2020_aimers.R
import com.google.firebase.FirebaseApp

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        FirebaseApp.initializeApp(applicationContext)
    }
}