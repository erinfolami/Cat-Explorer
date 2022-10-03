package com.example.catexplorer.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.catexplorer.utils.DataStoreConstants.userKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager @Inject constructor(@ApplicationContext applicationContext: Context) {

    private val context = applicationContext


    suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit {
            //making sure only one unique value is saved
            if (!it.contains(preferencesKey)) {
                it[preferencesKey] = value
            }
        }
    }


    val preferencesKey = stringPreferencesKey(userKey)
    val shrdFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[preferencesKey] ?: "null"
    }

}