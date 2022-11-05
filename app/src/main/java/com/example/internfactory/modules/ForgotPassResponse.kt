package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class ForgotPassResponse (
    @SerializedName("message") val message:String?,
    @SerializedName("success") val success:String?,
    @SerializedName("email") val email:String?
)