package com.example.inout2020_aimers.appblock.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item : BlockedApps)

    @Delete
    suspend fun delete(item : BlockedApps)

    @Query("SELECT * FROM tbl_blockedApps")
    fun getBlockedApps() : LiveData<List<BlockedApps>>

}