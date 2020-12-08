package com.example.inout2020_aimers.bucket.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(Application()) {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)

    private val repository: ListRepository
    val allLists: LiveData<List<Bucket>>

    init {
        val listDao = ListRoomDatabase.getDatabase(application).listDao()
        repository = ListRepository(listDao)
        allLists = repository.allLists
    }

    fun insert(bucket: Bucket) = scope.launch(Dispatchers.IO) {
        repository.insert(bucket)
    }

    fun delete(bucket: Bucket) = scope.launch(Dispatchers.IO) {
        repository.delete(bucket)
    }

    fun update(bucket: Bucket) = scope.launch(Dispatchers.IO) {
        repository.update(bucket)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}