package com.example.inout2020_aimers.Milestones.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "goals_table")
data class Goals(
    @PrimaryKey(autoGenerate = true) var goalId: Long = 0L,
    var mainGoal: String = "",
    var mainGoalDate: String = "",
    var subGoal1: String = "",
    var subGoal1Date: String = "",
    var subGoal2: String = "",
    var subGoal2Date: String = "",
    var subGoal3: String = "",
    var subGoal3Date: String = ""
) :
    Parcelable
