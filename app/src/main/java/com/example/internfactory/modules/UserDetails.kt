package com.example.internfactory.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class UserDetails(val context: Context){
    val Context.dataStore:DataStore<Preferences> by preferencesDataStore("pref")

    companion object{
        val TOKEN= stringSetPreferencesKey("Token")
    }

    suspend fun storeUserData(name:String){
        context.dataStore.edit {
            it[TOKEN]= setOf(name)
        }
    }

    fun getName()=context.dataStore.data.map {
        it[TOKEN]?:""
    }
}