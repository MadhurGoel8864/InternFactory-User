package com.example.internfactory.server

import com.example.internfactory.modules.LoginRequest
import com.example.internfactory.modules.LoginResponse
import com.example.internfactory.modules.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApi {
    @POST("/api/auth/login")
    fun login(@Body request: LoginRequest) : Call<LoginResponse>

    @POST("api/auth/signup")
    fun signIn(@Body userSend: User) : Call<String>
}