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
    fun signIn(@Body userSend: User) : Call<SignUpResponse>

    @POST("/api/auth/forget")
    fun forgotPassword(@Body email : Email) : Call<ForgotPassResponse>

    @POST("/api/auth/verifyotp")
    fun verifyotp(@Body verifyOtp: VerifyOtp) : Call<VerifyOtpResponse>

    @POST("api/auth/resetpass")
    fun resetPassRequest(@Body resetPassRequest: ResetPassRequest) : Call<ResetPasswordResponse>

    @GET("/api/getCategory")
    fun getcategories() : Call<MutableList<category_seeall_response>>

    @GET("/api/getTrending")
    fun gettrends(): Call<MutableList<trending_seeall_response>>

}