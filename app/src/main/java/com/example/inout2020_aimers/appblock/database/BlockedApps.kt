package com.example.inout2020_aimers.appblock.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_blockedApps")
class BlockedApps(
    @PrimaryKey(autoGenerate = false)
    val packageName : String
) {

    /*@PrimaryKey(autoGenerate = true)
    var id : Int? = null*/
}