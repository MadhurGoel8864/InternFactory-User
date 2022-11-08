package com.example.internfactory.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class UserDetails(val context: Context){

    companion object{
        val Context.dataStore:DataStore<Preferences> by preferencesDataStore("pref")
        val token= stringPreferencesKey("token")
        val loginInState= booleanPreferencesKey("logInState")
        val email= stringPreferencesKey("email")
    }

    suspend fun storeUserData(logInInfo: LogInInfo){
        context.dataStore.edit {
            it[token]= logInInfo.token
            it[loginInState]=logInInfo.logInState
            it[email]=logInInfo.signInemail
        }
    }

    fun getToken()=context.dataStore.data.map {
        LogInInfo(
            token=it[token]?:"",
            logInState =it[loginInState]?:false,
            signInemail = it[email]?:""
        )
    }
}