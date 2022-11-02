package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class User(
    @SerializedName("firstname") var firstName : String?,
    @SerializedName("lastname") var lastName : String?,
    @SerializedName("email") var email : String?,
    @SerializedName("password") var password : String?
    )
