package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") var authToken: String?,
    @SerializedName("message") var message:String?,
    @SerializedName("success") var success:String?
)
