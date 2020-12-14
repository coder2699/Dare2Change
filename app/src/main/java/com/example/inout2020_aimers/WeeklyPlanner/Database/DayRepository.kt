package com.example.inout2020_aimers.WeeklyPlanner.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class DayRepository (private val dayDao: DayDao) {

    val allDays: LiveData<List<Day>> = dayDao.getAllProfile()

    @WorkerThread
    fun insert(day: Day) {
        dayDao.insertDay(day)
    }

    @WorkerThread
    fun delete(day: Day) {
        dayDao.deleteDay(day)
    }
    @WorkerThread
    fun update(day: Day) {
        dayDao.updateDay(day)
    }
}