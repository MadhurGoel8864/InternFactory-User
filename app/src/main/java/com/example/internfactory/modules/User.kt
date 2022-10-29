package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("firstName") var firstName : String?,
    @SerializedName("lastName") var lastName : String?,
    @SerializedName("email") var email : String?,
    @SerializedName("password") var password : String?
    )
