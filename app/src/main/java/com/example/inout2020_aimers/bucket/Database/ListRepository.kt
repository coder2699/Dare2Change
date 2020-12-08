package com.example.inout2020_aimers.bucket.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ListRepository(private val listDao: ListDao) {

    val allLists: LiveData<List<Bucket>> = listDao.getAllProfile()

    @WorkerThread
    fun insert(bucket: Bucket) {
        listDao.insertBucket(bucket)
    }

    @WorkerThread
    fun delete(bucket: Bucket) {
        listDao.deleteBucket(bucket)
    }
    @WorkerThread
    fun update(bucket: Bucket) {
        listDao.updateBucket(bucket)
    }
}