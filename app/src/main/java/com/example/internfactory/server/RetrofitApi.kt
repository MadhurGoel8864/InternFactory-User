package com.example.internfactory.server

import com.example.internfactory.modules.Email
import com.example.internfactory.modules.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @POST("/api/auth/login")
    fun login(@Body userSend: User) : Call<User>

    @POST("api/auth/signup")
    fun signIn(@Body userSend: User) : Call<String>

    @GET("api/")
    suspend fun getDetails(): Response<User>

    @POST("api/auth/forget")
    fun forgotPassword(@Body email : Email) : Call<String>
}