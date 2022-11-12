package com.example.internfactory.modules

import com.google.gson.annotations.SerializedName

data class ApplyInternshipRequest(
    @SerializedName("why_should_we_hire_you") val whyShouldWeHireYou:String?,
    @SerializedName("share_your_work") val ShareYourWork:String?,
    @SerializedName("worked_in_team") val WorkedInTeam:String,
    @SerializedName("strengths") val Strength:String?,
    @SerializedName("weakness") val Weakness:String?,
    @SerializedName("hobbies") val Hobbies:String?
)
