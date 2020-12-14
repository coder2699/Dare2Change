package com.example.inout2020_aimers.WeeklyPlanner.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DayDao
{
    @Query("SELECT * FROM week_table ORDER BY id ")
    fun getAllProfile(): LiveData<List<Day>>

    @Insert
    fun insertDay(vararg day: Day)

    @Delete
    fun deleteDay(vararg day: Day)

    @Update
    fun updateDay(vararg day: Day)
}