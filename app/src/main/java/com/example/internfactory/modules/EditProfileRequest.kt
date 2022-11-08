package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("email") val email:String?
)
