package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class ResetPassRequest (
    @SerializedName ("email") var email :String?,
    @SerializedName ("password") var password:String?,
    @SerializedName("conformpassword") var con_pass:String?
        )