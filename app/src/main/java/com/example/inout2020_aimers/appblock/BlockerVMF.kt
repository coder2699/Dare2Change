package com.example.inout2020_aimers.appblock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inout2020_aimers.appblock.database.BlockedAppsRepository

class BlockerVMF(
    private val repository: BlockedAppsRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BlockerViewModel(repository) as T
    }

}