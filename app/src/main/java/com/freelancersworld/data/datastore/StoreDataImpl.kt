package com.freelancersworld.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreDataImpl @Inject constructor(private val context:Context) : StoreData {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("freelancers-world-pref")

        val USER_ID = longPreferencesKey("userId")
        val USERNAME = stringPreferencesKey("userName")
    }

    override suspend fun setUserId(id: Long) {
        save(USER_ID, id)
    }

    override suspend fun getUserId(): Flow<Long?> = get(USER_ID, 0L)


    override suspend fun setUserName(name: String) {
        save(USERNAME, name)
    }

    override suspend fun getUserName(): Flow<String?> = get(USERNAME, "")

    private suspend fun save(key: Preferences.Key<String>, value:String) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private suspend fun save(key: Preferences.Key<Long>, value: Long) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    private suspend fun get(key: Preferences.Key<Long>, default:Long = 0) =
        context.dataStore.data.map { preferences ->
            preferences[key] ?: default
        }

    private suspend fun get(key: Preferences.Key<String>, default:String = "") =
        context.dataStore.data.map { preferences ->
            preferences[key] ?: default
        }

}
