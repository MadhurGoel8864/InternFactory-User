package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("message") val message:String?,
    @SerializedName("success") val success:String?
)
