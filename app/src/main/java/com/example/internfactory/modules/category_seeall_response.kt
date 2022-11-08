package com.example.internfactory.modules

import android.os.Parcel
import android.os.Parcelable


data class category_seeall_response(
    val categoryId: Int?,
    val categoryName: String?,
    val imageName: String?,
    val count: Int?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(categoryId)
        parcel.writeString(categoryName)
        parcel.writeString(imageName)
        parcel.writeValue(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<category_seeall_response> {
        override fun createFromParcel(parcel: Parcel): category_seeall_response {
            return category_seeall_response(parcel)
        }

        override fun newArray(size: Int): Array<category_seeall_response?> {
            return arrayOfNulls(size)
        }
    }
}