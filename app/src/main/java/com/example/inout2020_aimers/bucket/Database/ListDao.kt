package com.example.inout2020_aimers.bucket.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListDao
{
    @Query("SELECT * FROM bucket_list_table")
    fun getAllProfile(): LiveData<List<Bucket>>

    @Insert
    fun insertBucket(vararg bucket: Bucket)

    @Delete
    fun deleteBucket(vararg bucket: Bucket)

    @Update
    fun updateBucket(vararg bucket: Bucket)
}