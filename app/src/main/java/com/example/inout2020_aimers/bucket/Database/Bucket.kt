package com.example.inout2020_aimers.bucket.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bucket_list_table")
data class Bucket(
    @PrimaryKey(autoGenerate = true) var listId: Long = 0L,
    var task: String = "",
    var check: Boolean = false
)