package com.example.inout2020_aimers.appblock.blockService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.inout2020_aimers.R
import com.example.inout2020_aimers.appblock.database.BlockedApps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates
import kotlin.random.Random

class AppBlockService() : Service() {

    private val CHANNEL_ID = "block-timer"
    private val NOTIFICATION_ID = 123

    private val TAG = "AppBlockService"
    lateinit var blockIntent : Intent
    lateinit var blockedApps : ArrayList<BlockedApps>
    var timeByUser by Delegates.notNull<Int>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        blockedApps = ArrayList()
        blockedApps = intent?.getSerializableExtra("blockedApps") as ArrayList<BlockedApps>

        timeByUser = intent?.getIntExtra("time",0)

        blockIntent= Intent(applicationContext, BlockActivity::class.java)
        blockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val usm = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        var currentApp = ""

        val countDownTimer = object : CountDownTimer(timeByUser*60000L,1000){
            override fun onTick(p0: Long) {

                sendNotification("Time remaining : ${(p0/1000).toString()}",
                    "Focus mode is ON")

                currentApp = "Didn't get app"
                val time = System.currentTimeMillis()

                val appStatsList = usm.queryUsageStats(
                    UsageStatsManager.INTERVAL_DAILY,
                    time - 1000 * 1000, time
                )

                // Getting foreground app
                if (appStatsList != null && !appStatsList.isEmpty()) {
                    currentApp = Collections.max(appStatsList) { o1, o2 -> java.lang.Long.compare(
                        o1.getLastTimeUsed(),
                        o2.getLastTimeUsed()
                    ) }.getPackageName()
                }


                for (app in blockedApps){
                    // Redirecting to Block Screen
                    if (app.packageName == currentApp.trim()){
                            blockIntent.putExtra("timeLeftUnblock",p0)
                            startActivity(blockIntent)
                        break
                    }
                }
            }

            override fun onFinish() {
                sendNotification("Focus time completed","Congratulations!")
            }
        }

        countDownTimer.start()
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    // Notification Channel Creation
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){

        val channelName = "ImportantChannel"
        val channel = NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_LOW).apply {
            description = "Channel is used to notify blocker timer."
        }

        notificationManager.createNotificationChannel(channel)

    }

    // Notifying time remaining
    private fun sendNotification(timeRemaining : String, title:String){

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification Channel Create function call
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            // Creating Channel
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_block_24)
                .setContentTitle(title)
                .setContentText(timeRemaining)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

        val timerNotification = notificationBuilder.build()
        notificationManager.notify(NOTIFICATION_ID, timerNotification)
    }


}