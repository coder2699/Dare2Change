package com.example.inout2020_aimers.WeeklyPlanner.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "week_table")
data class Day(
    @PrimaryKey(autoGenerate = true) var dayId: Long = 0L,
    var dayTask: String = "",
    var dayName: String="",
    var id:Int=0
)