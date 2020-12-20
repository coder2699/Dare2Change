package com.example.inout2020_aimers.appblock.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tbl_blockedApps")
class BlockedApps (
    @PrimaryKey(autoGenerate = false)
    val packageName : String
) : Serializable{

}