package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class VerifyOtp(
    @SerializedName("one_time_password") var otp:String?,
    @SerializedName("email") var email:String?
)
