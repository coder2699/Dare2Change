package com.example.inout2020_aimers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.inout2020_aimers.ui.HomeActivity
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class MyCustomAppIntro : AppIntro() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this?.getSharedPreferences("FIRST_RUN", Context.MODE_PRIVATE)!!
        // Make sure you don't call setContentView!
        if (sharedPreferences.getInt("FIRST_RUN", 0) == 1)
            startActivity(Intent(this, HomeActivity::class.java))
        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.newInstance(
                title = "Welcome...",
                description = "This is the first slide of the example"
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Slide 2...",
                description = "This is the 2nd slide of the example"
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "...Let's get started!",
                description = "This is the last slide, I won't annoy you more :)"
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}