package com.example.inout2020_aimers.WeeklyPlanner.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class DayViewModel (application: Application) : AndroidViewModel(Application()) {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)

    private val repository: DayRepository
    val allDays: LiveData<List<Day>>
    val mListLivedata = MutableLiveData<List<Day>>()

    init {
        val listDao = DayRoomDatabase.getDatabase(application).dayDao()
        repository = DayRepository(listDao)
        allDays = repository.allDays
    }

    fun insert(day: Day) = scope.launch(Dispatchers.IO) {
        repository.insert(day)
    }

    fun getNewLivedata(newList: ArrayList<Day>) {

        for(i in newList){
            i.dayTask=""
            i.done= 0
            delete(i)
        }
        val day0 = Day(
            dayTask = "",
            dayName = "MONDAY",
            id = 0
        )
        val day1 = Day(
            dayTask = "",
            dayName = "TUESDAY",
            id = 1
        )
        val day2 = Day(
            dayTask = "",
            dayName = "WEDNESDAY",
            id = 2
        )
        val day3 = Day(
            dayTask = "",
            dayName = "THURSDAY",
            id = 3
        )
        val day4 = Day(
            dayTask = "",
            dayName = "FRIDAY",
            id = 4
        )
        val day5 = Day(
            dayTask = "",
            dayName = "SATURDAY",
            id = 5
        )
        val day6 = Day(
            dayTask = "",
            dayName = "SUNDAY",
            id = 6
        )
        insert(day0)
        insert(day1)
        insert(day2)
        insert(day3)
        insert(day4)
        insert(day5)
        insert(day6)


    }

    fun delete(day: Day) = scope.launch(Dispatchers.IO) {
        repository.delete(day)
    }

    fun update(day: Day) = scope.launch(Dispatchers.IO) {
        repository.update(day)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}