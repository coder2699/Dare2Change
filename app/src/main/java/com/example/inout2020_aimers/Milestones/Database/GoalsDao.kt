package com.example.inout2020_aimers.Milestones.Database
 
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface GoalsDao
{
    @Query("SELECT * FROM goals_table")
    fun getAllGoals(): LiveData<List<Goals>>

    @Insert
    fun insertGoals(vararg Goals: Goals)

    @Delete
    fun deleteGoals(vararg Goals: Goals)

    @Update
    fun updateGoals(vararg Goals: Goals)
}