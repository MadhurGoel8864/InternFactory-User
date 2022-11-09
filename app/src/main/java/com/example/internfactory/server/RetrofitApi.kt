package com.example.internfactory.server

import com.example.internfactory.modules.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface RetrofitApi {
    //Auth
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

    //DashBoard
    @GET("/api/getCategory")
    fun getcategories(@Header ("Authorization") token :String) : Call<MutableList<category_seeall_response>>

    @GET("/api/getTrending")
    fun gettrends(@Header ("Authorization") token :String): Call<MutableList<trending_seeall_response>>

    //Profile
    @POST("/api/getUserInfo")
    fun viewProfile(@Body editProfileRequest: EditProfileRequest,@Header ("Authorization") token :String): Call<EditProfileResponse>

    @PUT("/api/editUserInfo")
    fun editProfile(@Body viewProfile: ViewProfile,@Header ("Authorization") token :String): Call<ViewProfileResponse>

}