package com.example.inout2020_aimers.WeeklyPlanner.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DayViewModel (application: Application) : AndroidViewModel(Application()) {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)

    private val repository: DayRepository
    val allDays: LiveData<List<Day>>

    init {
        val listDao = DayRoomDatabase.getDatabase(application).dayDao()
        repository = DayRepository(listDao)
        allDays = repository.allDays
    }

    fun insert(day: Day) = scope.launch(Dispatchers.IO) {
        repository.insert(day)
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