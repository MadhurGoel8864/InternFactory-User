package com.example.internfactory.server

import com.example.internfactory.modules.LoginRequest
import com.example.internfactory.modules.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
        @POST(Constants.LOGIN_URL)
        @FormUrlEncoded
        fun login(@Body request: LoginRequest): Call<LoginResponse>
}