package com.freelancersworld.data.datastore

import kotlinx.coroutines.flow.Flow

interface StoreData {
    suspend fun setUserId(id:Long)
    suspend fun getUserId() : Flow<Long?>

    suspend fun setUserName(name:String)
    suspend fun getUserName() : Flow<String?>
}