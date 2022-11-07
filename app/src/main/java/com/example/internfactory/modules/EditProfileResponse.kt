package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("email") val email:String?,
    @SerializedName("firstname") val firstname:String?,
    @SerializedName("lastname") val lastname:String?,
    @SerializedName("gender") val gender:String?,
    @SerializedName("newemail") val newemail:String?,
    @SerializedName("phoneNumber") val phoneNumber:Int?
)
