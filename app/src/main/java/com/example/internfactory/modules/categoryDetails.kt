package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class categoryDetails(
    @SerializedName("categoryId") val categoryId:Int?,
    @SerializedName("categoryName") val categoryname:String?,
    @SerializedName("imageName") val imagename:String?,
    @SerializedName("count") val count:Int?
)
