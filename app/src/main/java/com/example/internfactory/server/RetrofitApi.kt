package com.example.internfactory.server

import com.example.internfactory.modules.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @POST("/api/auth/login/")
    fun login(@Body userSend: User) : Call<LoginResponse>

    @POST("/api/auth/signup")
    fun signIn(@Body userSend: User) : Call<String>

    @POST("/api/auth/forget")
    fun forgotPassword(@Body email : Email) : Call<String>

    @POST("/api/auth/verifyotp")
    fun verifyotp(@Body verifyOtp: VerifyOtp) : Call<String>

    @POST("api/auth/resetpass")
    fun resetpass(@Body resetpass: ResetPassword) : Call<String>
}