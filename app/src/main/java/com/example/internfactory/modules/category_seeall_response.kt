package com.example.internfactory.modules

import android.os.Parcel
import android.os.Parcelable


data class category_seeall_response(
    val categoryId: Int?,
    val categoryName: String?,
    val imageName: String?,
    val count: Int?
)