package com.example.internfactory.server

import com.example.internfactory.modules.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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


    @GET("/api/getCategory")
    fun getcategories1(@Header ("Authorization") token :String) : Call<MutableList<main_screen_category_dataclass>>



    @GET("/api/getTrending")
    fun gettrends(@Header ("Authorization") token :String): Call<MutableList<trending_seeall_response>>

    //Profile
    @POST("/api/getUserInfo")
    fun viewProfile(@Body editProfileRequest: EditProfileRequest
                    ,@Header ("Authorization") token :String): Call<EditProfileResponse>

    @PUT("/api/editUserInfo")
    fun editProfile(@Body viewProfile: ViewProfile
                    ,@Header ("Authorization") token :String): Call<ViewProfileResponse>

    //Internships
    @POST("/api/user/{email}/internships/{id}/apply")
    fun applyinternship(@Path("email") email:String,@Path("id") id:Int
                        ,@Body applyinternrequest: Applyinternrequest
                        ,@Header ("Authorization") token :String):Call<Applyinternresponse>

    @POST("/api/internships/search/{searchtext}")
    fun searchapi(@Path("searchtext") searchtext:String
                  ,@Body searchingRequest: SearchingRequest
                  ,@Header ("Authorization") token :String): Call<SearchingResponse>

    @POST("/api/category/{id}/allinternships")
    fun allinternship(@Path("id") id:Int
                      ,@Body internshipRequest: Internship_request
                      ,@Header ("Authorization") token :String): Call<Internship_response>

    @GET("/api/getinternships/{InternshipID}")
    fun internshipDetail(@Path("InternshipID") InternshipID:Int
                         ,@Header ("Authorization") token :String):Call<InternshipDetail_response>

    @GET("/api/getAssessment/internhip/{InternshipId}")
    fun internshipquestion(@Path("InternshipId") InternshipId:Int
                           ,@Header ("Authorization") token :String):Call<ApplyInternship_response>

}