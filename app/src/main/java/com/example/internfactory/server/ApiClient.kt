package com.example.internfactory.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: RetrofitApi

    fun getApiService(): RetrofitApi {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(" https://internfactory.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(RetrofitApi::class.java)
        }
        return apiService
    }
}