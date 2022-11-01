package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class User(
    @Field("firstName") var firstName : String?,
    @Field("lastName") var lastName : String?,
    @Field("email") var email : String?,
    @Field("password") var password : String?
    )
