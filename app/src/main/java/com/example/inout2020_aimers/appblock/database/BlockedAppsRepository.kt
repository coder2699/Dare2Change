package com.example.inout2020_aimers.appblock.database

class BlockedAppsRepository(
    private val db : BlockerDatabase
) {

    suspend fun upsert(item:BlockedApps) = db.getAppsDao().upsert(item)

    suspend fun delete(item: BlockedApps) = db.getAppsDao().delete(item)

    fun getBlockedApps() = db.getAppsDao().getBlockedApps()

}