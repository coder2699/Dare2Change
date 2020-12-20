package com.example.inout2020_aimers.appblock.blockService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.databinding.ActivityBlockBinding
import kotlinx.android.synthetic.main.activity_block.*

class BlockActivity : AppCompatActivity() {
    private val TAG = "BlockActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_block)

        val timeLeft = intent.getLongExtra("timeLeftUnblock",0)
        Log.d(TAG, "onCreate: timeLeft -> $timeLeft")


        val countDownTimer = object : CountDownTimer(timeLeft+2000,1000){
            override fun onTick(p0: Long) {
                Log.d(TAG, "onTick in block activity: $p0")
                val inSec = p0/1000
                tvBlockActTimer.text = inSec.toString()
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish: FINISHED")
                tvBlockActTimer.text = "Finished"
                moveTaskToBack(true)
            }
        }
        countDownTimer.start()

    }
}