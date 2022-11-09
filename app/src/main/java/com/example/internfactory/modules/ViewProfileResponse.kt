package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class ViewProfileResponse(
    @SerializedName("message") val message:String?,
    @SerializedName("success") val success:String?,
    @SerializedName("newemail") val newemail:String?,
    @SerializedName("email") val email:String?
)
