package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("id") val id:String?,
    @SerializedName("profilePhoto") val profilePhoto:String?,
    @SerializedName("email") val email:String?,
    @SerializedName("firstname") val firstname:String?,
    @SerializedName("lastname") val lastname:String?,
    @SerializedName("gender") val gender:String?,
    @SerializedName("phoneNumber") val phoneNumber:String?,
    @SerializedName("message") val message:String?,
    @SerializedName("success") val success:Boolean?
)
