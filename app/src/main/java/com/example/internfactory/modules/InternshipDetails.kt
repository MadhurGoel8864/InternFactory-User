package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class InternshipDetails(
    @SerializedName("id") val id:Int?,
    @SerializedName("title") val title:String?,
    @SerializedName("type") val type:String?,
    @SerializedName("tenure") val tenure:String?,
    @SerializedName("stipned") val stipned:String?,
    @SerializedName("lastDate") val lastDate:Int?,
    @SerializedName("about") val about:String?,
    @SerializedName("skills") val skills:String?,
    @SerializedName("who_can_apply") val whoCanApply:String?,
    @SerializedName("perks") val perks:String?,
    @SerializedName("imageUrl") val imageurl:String?,
    @SerializedName("issuedDate") val issuedate:Int?,
    @SerializedName("category") val category:categoryDetails?
)
