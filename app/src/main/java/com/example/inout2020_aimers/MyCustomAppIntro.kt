package com.example.inout2020_aimers

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.inout2020_aimers.ui.HomeActivity
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType

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

        setColorDoneText(ContextCompat.getColor(this, R.color.white))
        setColorSkipButton(ContextCompat.getColor(this, R.color.white))
        setNextArrowColor(R.color.white)
        setIndicatorColor(
            selectedIndicatorColor = ContextCompat.getColor(this, R.color.white),
            unselectedIndicatorColor = ContextCompat.getColor(this, R.color.blackShade)
        )
        setTransformer(AppIntroPageTransformerType.SlideOver)
        setImmersiveMode()
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_app_intro))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_goals_intro))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_bucket_intro))
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_tips_intro))
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