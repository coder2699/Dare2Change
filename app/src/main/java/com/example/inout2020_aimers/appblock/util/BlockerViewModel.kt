package com.example.inout2020_aimers.appblock.util

import androidx.lifecycle.ViewModel
import com.example.inout2020_aimers.appblock.database.BlockedApps
import com.example.inout2020_aimers.appblock.database.BlockedAppsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlockerViewModel(
    private val repository : BlockedAppsRepository
) : ViewModel(){

    fun upsert(item : BlockedApps) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(item)
    }

    fun delete(item: BlockedApps) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }

    fun getBlockedApps() = repository.getBlockedApps()

}