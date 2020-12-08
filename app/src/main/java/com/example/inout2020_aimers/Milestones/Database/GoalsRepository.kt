package com.example.inout2020_aimers.Milestones.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class GoalsRepository(private val goalsDao: GoalsDao) {

    val allGoals: LiveData<List<Goals>> = goalsDao.getAllGoals()

    @WorkerThread
    fun insert(goals: Goals) {
        goalsDao.insertGoals(goals)
    }

    @WorkerThread
    fun delete(goals: Goals) {
        goalsDao.deleteGoals(goals)
    }
    @WorkerThread
    fun update(goals: Goals) {
        goalsDao.updateGoals(goals)
    }
}