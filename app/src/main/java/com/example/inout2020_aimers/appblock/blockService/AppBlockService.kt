package com.example.inout2020_aimers.appblock.blockService

import android.app.Service
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import com.example.inout2020_aimers.appblock.database.BlockedApps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class AppBlockService() : Service() {

    private val TAG = "AppBlockService"
    lateinit var blockIntent : Intent
    lateinit var blockedApps : ArrayList<BlockedApps>
    var timeByUser by Delegates.notNull<Int>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        blockedApps = ArrayList()
        blockedApps = intent?.getSerializableExtra("blockedApps") as ArrayList<BlockedApps>

        timeByUser = intent?.getIntExtra("time",0)

        Log.d(TAG, "onStartCommand: Time sent by user -> $timeByUser ")

        blockIntent= Intent(applicationContext, BlockActivity::class.java)
        blockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        Log.d(TAG, "onCreate: SERVICE STARTED")
        val usm = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

//        val app = usm.packageName

        var count  = 0
        var currentApp = ""

        Log.d(TAG, "onCreate: SECONDS TO FINISH -> ${timeByUser*60000L}")


        val countDownTimer = object : CountDownTimer(timeByUser*60000L,1000){
            override fun onTick(p0: Long) {
                Log.d(TAG, "onTick: ${p0/1000}")
                currentApp = "Didn't get app"
                val time = System.currentTimeMillis()

                val appStatsList = usm.queryUsageStats(
                    UsageStatsManager.INTERVAL_DAILY,
                    time - 1000 * 1000, time
                );
                if (appStatsList != null && !appStatsList.isEmpty()) {
                    currentApp = Collections.max(appStatsList) { o1, o2 -> java.lang.Long.compare(
                        o1.getLastTimeUsed(),
                        o2.getLastTimeUsed()
                    ) }.getPackageName()
                    Log.d(TAG, "onReceive: CURRENt APP -> $currentApp")
                }


                for (app in blockedApps){
                    Log.d(TAG, "onCreate: In for loop -> ${app.packageName}")
                    if (app.packageName == currentApp.trim()){
                        Log.d(TAG, "onTick: BLOCKED----------------------------")
                            blockIntent.putExtra("timeLeftUnblock",p0)
                            startActivity(blockIntent)

//                        val open = packageManager.getLaunchIntentForPackage("com.astro.appblocktest")
//                        startActivity(open)
                        break

                    }

                }
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish: FINISHED")
            }
        }

        countDownTimer.start()
        Log.d(TAG, "onCreate: CountDown Started")

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


}