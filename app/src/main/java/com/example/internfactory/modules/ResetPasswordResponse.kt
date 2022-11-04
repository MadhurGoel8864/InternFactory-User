package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("message") var message:String?,
    @SerializedName("success") var success:String?
)
