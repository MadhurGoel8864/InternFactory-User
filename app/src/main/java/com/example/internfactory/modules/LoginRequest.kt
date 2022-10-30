package com.example.internfactory.modules

import retrofit2.http.Field

data class LoginRequest(
    @Field("email") var email : String?,
    @Field("password") var password : String?)
