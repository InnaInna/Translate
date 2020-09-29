package com.translate.data.entity.presentation

import android.os.Parcel
import android.os.Parcelable

data class NetworkErrorUIModel(
    val code: Int,
    val message: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(code)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NetworkErrorUIModel> {
        override fun createFromParcel(parcel: Parcel): NetworkErrorUIModel {
            return NetworkErrorUIModel(parcel)
        }

        override fun newArray(size: Int): Array<NetworkErrorUIModel?> {
            return arrayOfNulls(size)
        }
    }
}