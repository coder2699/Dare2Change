package com.example.inout2020_aimers.Milestones.Database


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GoalsViewModel(application: Application) : AndroidViewModel(Application()) {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)

    private val repository: GoalsRepository
    val allLists: LiveData<List<Goals>>

    init {
        val listDao = GoalsRoomDatabase.getDatabase(application).goalsDao()
        repository = GoalsRepository(listDao)
        allLists = repository.allGoals
    }

    fun insert(goals: Goals) = scope.launch(Dispatchers.IO) {
        repository.insert(goals)
    }

    fun delete(goals: Goals) = scope.launch(Dispatchers.IO) {
        repository.delete(goals)
    }

    fun update(goals: Goals) = scope.launch(Dispatchers.IO) {
        repository.update(goals)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}