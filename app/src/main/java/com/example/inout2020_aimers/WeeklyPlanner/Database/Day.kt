package com.example.inout2020_aimers.WeeklyPlanner.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "week_table")
data class Day(
    @PrimaryKey(autoGenerate = true) var dayId: Long = 0L,
    var dayTask: String = "",
    var dayName: String="",
    var id:Int=0,
    var done:Int=0
)